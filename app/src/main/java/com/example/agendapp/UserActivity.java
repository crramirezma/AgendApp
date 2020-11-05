package com.example.agendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.agendapp.Clases.Usuario;
import com.example.agendapp.Login.SesionActual;
import com.example.agendapp.Menu.MenuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class UserActivity extends AppCompatActivity {

    //Variables para la consulta json
    RequestQueue rq;
    JsonRequest jrq;


    TextView nombre;
    TextView apellido;
    TextView ciudad;
    TextView carrera;
    TextView edad;

    Button modBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        iniciar();


    }

    private void iniciar(){
        Usuario usuario=SesionActual.usuarioActual;
        nombre=findViewById(R.id.nombreTxt);
        apellido=findViewById(R.id.apellidoTxt);
        ciudad=findViewById(R.id.ciudadTxt);
        carrera=findViewById(R.id.carreraTxt);
        edad=findViewById(R.id.edadTxt);

        modBt=findViewById(R.id.modBt);

        //asignando los correspondientes textos a cada edittext, primero comprobar que si halla datos en el usuario
        if(usuario.getNombre()==null){
            nombre.setText("No ha seleccionado nombre");
        }else{
            nombre.setText(usuario.getNombre());
        }

        if(usuario.getApellido()==null){
            apellido.setText("No ha seleccionado apellido");
        }else{
            apellido.setText(usuario.getApellido());
        }

        if(usuario.getCarrera()==null){
            carrera.setText("No ha seleccionado carrera");
        }else{
            carrera.setText(usuario.getCarrera());
        }

        if(usuario.getCiudad()==null){
            ciudad.setText("No ha seleccionado ciudad");
        }else{
            ciudad.setText(usuario.getCiudad());
        }

        if(usuario.getEdad()==0){
            edad.setText(0+"");
        }else{
            edad.setText(usuario.getEdad()+"");
        }


        final String edadS=usuario.getEdad()+"";
        //al principio no habra cambios, por lo que no es necesario oprimir el boton
        modBt.setClickable(false);

        //se calcula si debe o no activarse el boton
        nombre.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if (!nombre.getText().equals(SesionActual.usuarioActual.getNombre())){
                        modBt.setClickable(true);
                    }
                }
            }
        });
        apellido.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if (!apellido.getText().equals(SesionActual.usuarioActual.getApellido())){
                        modBt.setClickable(true);
                    }
                }
            }
        });
        ciudad.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if (!ciudad.getText().equals(SesionActual.usuarioActual.getCiudad())){
                        modBt.setClickable(true);
                    }
                }
            }
        });
        carrera.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if (!carrera.getText().equals(SesionActual.usuarioActual.getCarrera())){
                        modBt.setClickable(true);
                    }
                }
            }
        });
        edad.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if (!edad.getText().equals(edadS)){
                        modBt.setClickable(true);
                    }
                }
            }
        });

    }

    public void returnOnClick(View view) {
        Intent intent=new Intent(this, MenuActivity.class);
        this.startActivity(intent);
    }

    public void modificarListener(View view) {
        String usuario=SesionActual.usuarioActual.getUsuario();
        String nomb=nombre.getText().toString();
        String ape=apellido.getText().toString();
        String ciu=ciudad.getText().toString();
        String carre=carrera.getText().toString();

        int eda;
        try{
            eda=Integer.parseInt(edad.getText().toString());
            modificar(usuario,nomb,ape,ciu,carre,eda);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Porfavor corregir la zona de edad",Toast.LENGTH_SHORT).show();
        }




    }

    private void modificar(String usuario, String nom, String ap, String ciud, String car, int ed){
        String url="http://agendapp.atwebpages.com/registro2.php?usuario="+usuario+"&nombre="+nom+"&apellido="+ap+"&ciudad="+ciud+"&carrera="+car+"&edad="+ed;
        url=url.replace(" ","%20");



        final String nomb=nom;
        final String ape=ap;
        final String ciu=ciud;
        final String carr=car;
        final int eda=ed;
        jrq=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                boolean v;
                try {
                    JSONArray json=response.optJSONArray("modificado");
                    v=json.getBoolean(0);

                    if(v){
                        Toast.makeText(getApplicationContext(),"Se han modificado los datos con exito,   ",Toast.LENGTH_SHORT ).show();
                        int contrasena=SesionActual.usuarioActual.getContraseña();
                        String usuarioA=SesionActual.usuarioActual.getUsuario();
                        Usuario usuario=new Usuario(usuarioA,contrasena,nomb,ape,carr,eda,ciu);
                        usuario.setAsignaturas(SesionActual.usuarioActual.getAsignaturas());
                        SesionActual.usuarioActual=usuario;

                        Intent intent =new Intent(UserActivity.this, MenuActivity.class);
                        UserActivity.this.startActivity(intent);
                        UserActivity.this.finish();
                    }else{
                        Toast.makeText(getApplicationContext(),"No se pudo modificar", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Error modificándo los datos, porfavor intentalo mas tarde",Toast.LENGTH_SHORT).show();
            }
        });
        rq = Volley.newRequestQueue (getApplicationContext ());
        rq.add(jrq);
    }
}