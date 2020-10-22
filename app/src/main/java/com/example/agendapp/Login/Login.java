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


    }

    public void crearOnClick(View view) {
        Intent Registro = new Intent ( this, Register.class );
        Login.this.startActivity(Registro);
    }


    private void IniciarSesión(){

    }
    @Override
    public void onErrorResponse ( VolleyError error ) {
        Toast.makeText ( getApplicationContext (),error.toString (),Toast.LENGTH_SHORT).show ();
    }
    @Override
    public void onResponse ( JSONObject response ) {
        int id;
        String nombre;
        String apellido;
        char tipo;
        String usuario;
        String r;

        try {
            JSONArray json=response.optJSONArray("usuario");

            JSONObject jsonObject=json.getJSONObject(0);
            id=jsonObject.optInt("id");
            nombre=jsonObject.optString("nombre");
            apellido=jsonObject.optString("apellido");

            r=jsonObject.optString("tipo");
            tipo=r.charAt(0);
            usuario=user.getText().toString();
            Character tip=tipo;


            Usuario User = new Usuario (nombre, apellido, tipo, usuario);
            User.setId(id);




            if(User.getId()==-1){
                Toast.makeText ( getApplicationContext (),"No se encontro el usuario", Toast.LENGTH_SHORT ).show ();
            }else if(User.getId()==-2){
                Toast.makeText ( getApplicationContext (),"error al conectar con la base", Toast.LENGTH_SHORT ).show ();
            }else{
                //Toast.makeText ( getApplicationContext (),"Bienvenido", Toast.LENGTH_SHORT ).show ();

                UsuarioActual.usuario=User;
                Intent cambio1 = new Intent( getApplicationContext(), opciones.class );
                startActivity ( cambio1 );
                login.this.finish ();

            }



        } catch (JSONException e) {
            e.printStackTrace();
        }



    }


}
