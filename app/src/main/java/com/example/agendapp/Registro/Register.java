package com.example.agendapp.Registro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.agendapp.Login.Login;
import com.example.agendapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Register extends AppCompatActivity {
    ///agendapp.atwebpages.com/registro.php

    //Variables para la consulta json
    RequestQueue rq;
    JsonRequest jrq;

    //Variables para las vistas
    EditText usuarioTxt;
    EditText contraseñaTxt;
    EditText rContraseñaTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        inicializar();
    }

    //funcion para inicializacion de variables
    private void inicializar(){
        usuarioTxt=findViewById(R.id.usuarioTxt);

        contraseñaTxt=findViewById(R.id.contraseñaTxt);
        rContraseñaTxt=findViewById(R.id.rcontraseñaTxt);
    }

    public void registroListener(View view) {
        String usuario=usuarioTxt.getText().toString();
        String contraseña=contraseñaTxt.getText().toString();
        if(validar(usuario, contraseña)){
            String url="http://agendapp.atwebpages.com/registro.php?usuario="+usuario+"&clave="+contraseña;
            registrar(url);
        }else{
            Toast.makeText(getApplicationContext(),"Error en los datos suministrados, vuelva a intentarlo",Toast.LENGTH_SHORT).show();
        }
    }

    public void inicioSListener(View view) {

        Register.this.finish();
    }

    private boolean validar(String usuario, String contraseña){
        boolean v=true;


        //validando que se halla escrito nombre de usuario
        if(usuario.length()==0){
            /*programar mensajito para esto*/
            v=false;
        }
        //validando tamaño de codigo
        if(contraseña.length()!=4){
            /*programar mensajito para esto*/
            v=false;
        }
        //validando que los dos codigos sean iguales
        if(!contraseña.equals(rContraseñaTxt.getText().toString())){
            /*programar mensajito para esto*/
            v=false;
        };

        return v;
    }

    private void registrar(String url){
        jrq=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                boolean v;
                try {
                    JSONArray json=response.optJSONArray("insertado");
                    v=json.getBoolean(0);

                    if(v){
                        Toast.makeText(getApplicationContext(),"Se ha creado el nuevo usuario con exito",Toast.LENGTH_SHORT ).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"usuario ya existe", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Error en la connección a la base de datos",Toast.LENGTH_SHORT).show();
            }
        });

        rq = Volley.newRequestQueue (getApplicationContext ());
        rq.add(jrq);
    }
}
