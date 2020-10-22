package com.example.agendapp.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import com.example.agendapp.Clases.Usuario;
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
    }


    /**
     * ZONA DE LISTENERS
     * las siguiente funciones seran los listeners de los botones y demas objetos declarados en el xml correspondiente
     */
    public void IniciarOnClick(View view) {
        //validando datos
        boolean validado=true;
        String usuario=userTxt.getText().toString();
        int cont=0;
        try{
            cont=Integer.parseInt(contraseñaTxt.getText().toString());
        }catch(Exception e){
            validado=false;
        }
        if(usuario.length()==0){
            validado=false;
            //mensaje para el usuario

        }

        if(Integer.toString(cont).length()!=4){
            validado=false;
        }

        if(validado){
            IniciarSesión(usuario, cont);
        }else{
            Toast.makeText(getApplicationContext(),"No se pudo realizar el inicio de sesión, vuelve a intentarlo",Toast.LENGTH_SHORT).show();
        }
    }

    public void crearOnClick(View view) {
        Intent Registro = new Intent ( this, Register.class );
        Login.this.startActivity(Registro);
    }


    private void IniciarSesión(String user,int pass){
        String url="http://agendapp.atwebpages.com/iniciodeSesion.php?usuario="+user+"&clave="+pass;
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
        try {
            JSONArray json=response.optJSONArray("Acceso");

            v=json.getBoolean(0);

            if(v){
                //crear usuario estatico

                Toast.makeText(getApplicationContext(),"Se a realizado la verificación con exito",Toast.LENGTH_SHORT ).show();
            }else{

                Toast.makeText(getApplicationContext(),"Usuario o contraseña no validos", Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }



    }


}
