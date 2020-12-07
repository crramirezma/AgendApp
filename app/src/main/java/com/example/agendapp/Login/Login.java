package com.example.agendapp.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.agendapp.Clases.Asignatura;
import com.example.agendapp.Clases.Tarea;
import com.example.agendapp.Clases.Usuario;
import com.example.agendapp.MainActivity;
import com.example.agendapp.Menu.MenuActivity;
import com.example.agendapp.R;
import com.example.agendapp.Registro.Register;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    //Variables para la consulta json
    RequestQueue rq;
    JsonRequest jrq;

    //variables de las vistas
    EditText userTxt;
    EditText contraseñaTxt;
    TextView usuarioLmsg;
    TextView contraseñaLmsg;

    Button iniciarBt;

    String usuario;
    int cont=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);


        //inicializando las vistas
        iniciar();
    }

    //la funcion "iniciar()", inicializa las vistas declaradas con anterioridad
    public void iniciar(){
        contraseñaTxt=findViewById(R.id.contraseñaTxt);
        userTxt= findViewById(R.id.usuarioTxt);

        usuarioLmsg=findViewById(R.id.usuarioLmsg);
        contraseñaLmsg=findViewById(R.id.contraseñaLmsg);

        iniciarBt=findViewById(R.id.iniciarBt);
    }


    /**
     * ZONA DE LISTENERS
     * las siguiente funciones seran los listeners de los botones y demas objetos declarados en el xml correspondiente
     */
    public void IniciarOnClick(View view) {
        iniciarBt.setClickable(false);

        //validando datos
        boolean validado=true;
        usuarioLmsg.setText("");
        contraseñaLmsg.setText("");

        usuario=userTxt.getText().toString();
        cont=0;
        try{
            cont=Integer.parseInt(contraseñaTxt.getText().toString());
        }catch(Exception e){
            validado=false;
        }
        if(usuario.length()==0){
            validado=false;
            //mensaje para el usuario
            usuarioLmsg.setText("Este campo es obligatorio");

        }

        if(Integer.toString(cont).length()!=4){
            validado=false;
            contraseñaLmsg.setText("La contraseña debe ser de cuatro digitos");
        }

        if(validado){
            IniciarSesión(usuario, cont);
        }else{
            Toast.makeText(getApplicationContext(),"No se pudo realizar el inicio de sesión, vuelve a intentarlo",Toast.LENGTH_SHORT).show();
            iniciarBt.setClickable(true);
        }
    }

    public void crearOnClick(View view) {
        Intent Registro = new Intent ( this, Register.class );
        Login.this.startActivity(Registro);
    }


    private void IniciarSesión(String user,int pass){
        String url="http://agendapp.atwebpages.com/iniciodeSesion.php?usuario="+user+"&clave="+pass;
        url=url.replace(" ","%20");
        jrq=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        rq = Volley.newRequestQueue (getApplicationContext ());
        rq.add(jrq);
    }

    @Override
    public void onErrorResponse ( VolleyError error ) {
        Toast.makeText ( getApplicationContext (),"No se pudo conectar con la base de datos"+error.toString(),Toast.LENGTH_SHORT).show ();
    }
    @Override
    public void onResponse ( JSONObject response ) {
        boolean v;

        Usuario user=new Usuario(usuario,cont);

        String nombre="";
        String apellido="";
        String carrera="";
        String ciudad="";
        int edad=0;

        try {
            JSONArray json=response.optJSONArray("Usuario");




            JSONObject jsonObject=json.getJSONObject(0);
            v=jsonObject.optBoolean("Acceso");

            if(v){
                nombre=jsonObject.optString("nombre");
                apellido=jsonObject.optString("apellido");
                carrera=jsonObject.optString("carrera");
                ciudad=jsonObject.optString("ciudad");
                edad=jsonObject.optInt("edad");

                //igual se le asignaran los valores al usuario
                user.setNombre(nombre);
                user.setApellido(apellido);
                user.setCarrera(carrera);
                user.setCiudad(ciudad);
                user.setEdad(edad);


                Toast.makeText(getApplicationContext(),"Se a realizado la verificación con exito",Toast.LENGTH_SHORT ).show();

                //se le asigna a un valor estatico el usuario
                SesionActual.usuarioActual=user;



                llenarAsignaturas();

            }else{
                iniciarBt.setClickable(true);
                Toast.makeText(getApplicationContext(),"Usuario o contraseña no validos", Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

    public void llenarAsignaturas(){
        String url="http://agendapp.atwebpages.com/Asignaturas/listarAsignaturas.php?usuario="+SesionActual.usuarioActual.getUsuario();
        url=url.replace(" ","%20");
        jrq=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Asignatura asig;


                JSONArray json;
                int id;
                int creditos;
                int imagen;

                boolean bloq=false;

                String nombreAsignatura;

                double tiempo;

                JSONObject jsonObject;


                json=response.optJSONArray("Asignaturas");

                try {
                    for(int i=0;i<json.length();i++){




                        jsonObject=json.getJSONObject(i);
                        id=jsonObject.optInt("id");
                        nombreAsignatura=jsonObject.optString("nombre");
                        creditos=jsonObject.optInt("creditos");
                        tiempo=jsonObject.optDouble("double");
                        imagen=jsonObject.optInt("imagen");

                        if(jsonObject.optInt("bloqueado")==1){
                            bloq=true;
                        }else{
                            bloq=false;
                        }


                        asig=new Asignatura(nombreAsignatura,creditos,tiempo,imagen);
                        asig.setId(id);
                        asig.setBloqueado(bloq);

                        SesionActual.usuarioActual.addAsignatura(asig);

                    }




                    //Zona de Intents y nuevas vistas
                    /* Gracias a que se declaran justo en el hilo donde se ejecuta la busqueda, la nueva vista vendra crgada y sincronizada con los datos del valor estatico*/

                    llenarTareas();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                llenarTareas();
                Toast.makeText ( getApplicationContext (),"No tienes asignaturas programadas",Toast.LENGTH_SHORT).show ();


            }
        });
        rq = Volley.newRequestQueue (getApplicationContext ());
        rq.add(jrq);
    }

    public void llenarTareas(){
        String url="http://agendapp.atwebpages.com/Tareas/listaTareas.php?usuario="+SesionActual.usuarioActual.getUsuario();
        url=url.replace(" ","%20");
        jrq=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Tarea tarea;


                JSONArray json;

                int id;
                int estado;
                String nombreTarea;


                JSONObject jsonObject;


                json=response.optJSONArray("Tareas");
                try {
                    for(int i=0;i<json.length();i++){




                        jsonObject=json.getJSONObject(i);
                        id=jsonObject.optInt("id");
                        nombreTarea=jsonObject.optString("nombre");
                        estado=jsonObject.optInt("estado");


                        tarea=new Tarea(nombreTarea,estado);
                        tarea.setId(id);

                        SesionActual.usuarioActual.addTarea(tarea);

                    }

                    //Zona de Intents y nuevas vistas
                    /* Gracias a que se declaran justo en el hilo donde se ejecuta la busqueda, la nueva vista vendra crgada y sincronizada con los datos del valor estatico*/


                    Intent intent = new Intent ( Login.this, MenuActivity.class );
                    Login.this.startActivity(intent);
                    Login.this.finish();




                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText ( getApplicationContext (),"No tienes tareas programadas",Toast.LENGTH_SHORT).show ();
                Intent intent = new Intent ( Login.this, MenuActivity.class );
                Login.this.startActivity(intent);
                Login.this.finish();

            }
        });
        rq = Volley.newRequestQueue (getApplicationContext ());
        rq.add(jrq);
    }


}
