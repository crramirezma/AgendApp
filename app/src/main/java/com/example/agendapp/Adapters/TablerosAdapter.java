package com.example.agendapp.Adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agendapp.Clases.TableroItem;
import com.example.agendapp.R;

import java.util.List;

public class TablerosAdapter extends RecyclerView.Adapter<TablerosAdapter.Tablerosholder> {

    private List<TableroItem> TablerosLista;

    public TablerosAdapter(List<TableroItem> tablerosLista) {
        TablerosLista = tablerosLista;
    }

    @NonNull
    @Override
    public Tablerosholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tableroitem, parent, false);

        return new Tablerosholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Tablerosholder holder, int position) {
        Uri imagen = TablerosLista.get(position).getImagen();
        String nombre = TablerosLista.get(position).getNombre();
        holder.setData(imagen, nombre);
    }

    @Override
    public int getItemCount() {
        return TablerosLista.size();
    }

    class Tablerosholder extends RecyclerView.ViewHolder{

        private ImageButton imagen;
        private TextView nombre;
        public Tablerosholder(@NonNull View itemView) {

            super(itemView);

            imagen = itemView.findViewById(R.id.imagenTablero);
            nombre = itemView.findViewById(R.id.nombreTablero);
        }

        public void setData(Uri imagen, String nombre) {
            this.imagen.setImageURI(imagen);
            this.nombre.setText(nombre);
        }



    }
}
