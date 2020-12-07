package com.example.agendapp.Tablero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Switch;




import com.example.agendapp.R;

public class Tablero extends AppCompatActivity {

    public static String nombreTablero;
    protected MyCanvas myCanvas;
    public static boolean debeCrear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablero);
        myCanvas = findViewById(R.id.Lienzo);
        myCanvas.context = this;

    }
    public void GuardarTablero(View view) {

        if(this.nombreTablero==null) {
            NuevoTableroDialog nuevotablero = new NuevoTableroDialog(this, this);
            nuevotablero.show(getSupportFragmentManager(), "Nuevo Dialogo");
        }
        else{
            this.myCanvas.guardarTablero(nombreTablero);
        }
    }

    public void cargarTablero(String nombre) {
        myCanvas.cargarTablero(nombre);
    }


    public void ColorRojo(View view) {
        //myCanvas.guardarTablero(); //
        myCanvas.borrado(false);
        myCanvas.lapiz.setColor(Color.RED);
    }

    public void ColorAzulMarin(View view) {
        myCanvas.borrado(false);
        myCanvas.lapiz.setColor(Color.rgb(53, 252, 181));
    }

    public void ColorNegro(View view) {
        myCanvas.borrado(false);
        myCanvas.lapiz.setColor(Color.WHITE);
    }

    public void Borrador(View view) {
        myCanvas.borrado(true);
    }


    public void tamaño1(View view){
        myCanvas.setTamaño(5f);
    }

    public void tamaño2(View view){
        myCanvas.setTamaño(10f);
    }

    public void tamaño3(View view){
        myCanvas.setTamaño(20f);
    }

    public void modoBrocha(View view){
        myCanvas.setmodoBrocha(true);
    }
    public void modoLapiz(View view){
        myCanvas.setmodoBrocha(false);
    }

    public String getNombreTablero() {
        return nombreTablero;
    }

    public void setNombreTablero(String nombreTablero) {
        this.nombreTablero = nombreTablero;
    }


    public void volver(View view) {
        nombreTablero = null;
        finish();
        if(debeCrear) {
            Intent intent;
            intent = new Intent(this, TableroActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.startActivity(intent);
            debeCrear = false;
        }

    }
}