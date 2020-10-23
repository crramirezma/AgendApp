package com.example.agendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.agendapp.Login.Login;
import com.example.agendapp.Login.SesionActual;
import com.example.agendapp.Registro.Register;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(SesionActual.usuarioActual==null){
            Intent Registro = new Intent ( this, Login.class );
            MainActivity.this.startActivity(Registro);
            this.finish();
        }else{
            Toast.makeText(getApplicationContext(),"hola mundo :"+SesionActual.usuarioActual.getCiudad(),Toast.LENGTH_LONG).show();
        }


    }
}
