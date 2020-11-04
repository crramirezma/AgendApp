package com.example.agendapp.tablero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
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

    private final int REQUEST_WRITE_EXTERNAL=0;
    private final int REQUEST_READ_EXTERNAL=1;
    private MyCanvas myCanvas;
    private Switch fondoSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Permisos();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablero);
        myCanvas = findViewById(R.id.Lienzo);


    }

    public void Permisos(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String []{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_WRITE_EXTERNAL);
        }else if(ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_READ_EXTERNAL);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if(requestCode==REQUEST_WRITE_EXTERNAL){
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getApplicationContext(),"Permiso Concedido",Toast.LENGTH_SHORT).show();

                }
                else
                    Toast.makeText(getApplicationContext(),"Permiso Denegado",Toast.LENGTH_SHORT).show();

            }else if(requestCode==REQUEST_READ_EXTERNAL){
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getApplicationContext(),"Permiso de READ_external Concedido",Toast.LENGTH_SHORT).show();

                }
                else
                    Toast.makeText(getApplicationContext(),"Permiso de READ_external Denegado",Toast.LENGTH_SHORT).show();

            }
        }

    public void GuardarTablero(View view) {
        //myCanvas.guardarTablero();
        myCanvas.cargarTablero("tablero1");

    }

    public void ColorRojo(View view) {
        myCanvas.guardarTablero(); //
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

}