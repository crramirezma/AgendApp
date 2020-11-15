package com.example.agendapp.Tablero;


import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agendapp.Adapters.TablerosAdapter;
import com.example.agendapp.Clases.TableroItem;
import com.example.agendapp.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListaTableros extends AppCompatActivity {
    private final int REQUEST_WRITE_EXTERNAL=0;
    private final int REQUEST_READ_EXTERNAL=1;
    private RecyclerView tableros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tableros);

        tableros = findViewById(R.id.tableros);
        Permisos();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        tableros.setLayoutManager(layoutManager);
        System.out.println("Error 404");
        File f = this.getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        File d = new File(f, "/Tableros/tablero1.jgp");
        List<TableroItem> tablerosLista = new ArrayList<>();

        tablerosLista.add(new TableroItem("Diego", Uri.fromFile(d)));

        TablerosAdapter adapter = new TablerosAdapter(tablerosLista);
        tableros.setAdapter(adapter);
        adapter.notifyDataSetChanged();

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
            if(grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
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


}