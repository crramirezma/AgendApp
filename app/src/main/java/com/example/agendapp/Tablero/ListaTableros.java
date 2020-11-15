package com.example.agendapp.Tablero;


import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agendapp.Adapters.TablerosAdapter;
import com.example.agendapp.Clases.TableroItem;
import com.example.agendapp.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListaTableros extends AppCompatActivity {
    private RecyclerView tableros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tableros);

        tableros = findViewById(R.id.tableros);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        tableros.setLayoutManager(layoutManager);

        File f = this.getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        File d = new File(f, "/Tableros/tablero1.jgp");
        List<TableroItem> tablerosLista = new ArrayList<>();

        tablerosLista.add(new TableroItem("Diego", Uri.fromFile(d)));

        TablerosAdapter adapter = new TablerosAdapter(tablerosLista);
        tableros.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}