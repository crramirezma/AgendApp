package com.example.agendapp.Clases;

import android.graphics.Bitmap;
import android.net.Uri;

public class TableroItem {
    private String nombre;
    private Bitmap imagen;
    private int id;
    public TableroItem(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
