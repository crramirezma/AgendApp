package com.example.agendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.agendapp.Login.Login;
import com.example.agendapp.Registro.Register;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent Registro = new Intent ( this, Login.class );
        MainActivity.this.startActivity(Registro);
    }
}
