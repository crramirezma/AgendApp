package com.example.agendapp.Tablero;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agendapp.Adapters.TablerosAdapter;
import com.example.agendapp.Clases.TableroItem;
import com.example.agendapp.Login.SesionActual;
import com.example.agendapp.Menu.ui.Asignaturas.subCarpetas.SubtemaActivity;
import com.example.agendapp.Menu.ui.Asignaturas.subCarpetas.SubtemaDialog;
import com.example.agendapp.R;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class TableroActivity extends AppCompatActivity {
    private final int REQUEST_WRITE_EXTERNAL=0;
    private final int REQUEST_READ_EXTERNAL=1;
    private RecyclerView tableros;
    private TextView subtemaTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tableros);
        subtemaTxt = findViewById(R.id.subtemaTxt);
        subtemaTxt.setText(SesionActual.subtema.getNombreSubtema());
        tableros = findViewById(R.id.tableros);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        tableros.setLayoutManager(layoutManager);

        TablerosAdapter adapter = new TablerosAdapter(this.llenarImagenTableros(SesionActual.subtema.getTableros()), this);
        tableros.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


    public void returnOnClick(View view) {
        finish();
    }

    public void nuevoTablero(View view) {

        if(SesionActual.subtema.getTableros().size()<5) {
            Intent intent;
            intent = new Intent(TableroActivity.this, Tablero.class);
            TableroActivity.this.startActivity(intent);
        }
        else{

            Toast.makeText(getApplicationContext(), "¡Límite de tableros!", Toast.LENGTH_SHORT).show();
        }
    }

    public List<TableroItem> llenarImagenTableros(List<TableroItem> tableros){

        for(TableroItem tablero: tableros){

            File f = this.getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
            File d = new File(f, "/Tableros/"+tablero.getNombre()+".jpg");

            if(d.exists()) {

                BitmapFactory.Options config = new BitmapFactory.Options();
                config.inPreferredConfig = Bitmap.Config.ARGB_8888;
                Bitmap imagen = BitmapFactory.decodeFile(d.getAbsolutePath());
                tablero.setImagen(imagen);
            }
            else{
                Toast.makeText(TableroActivity.this, "Hubo un problema al cargar el tablero"+ tablero.getNombre(), Toast.LENGTH_SHORT).show();
                continue;
            }

        }
        return tableros;
    }
}