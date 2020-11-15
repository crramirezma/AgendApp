package com.example.agendapp.Clases;

import android.net.Uri;

public class TableroItem {
    private String nombre;
    private Uri imagen;
    public TableroItem(String nombre, Uri imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }
    public void setImagen(Uri imagen) {
        this.imagen = imagen;
    }

    public Uri getImagen() {
        return imagen;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

}
