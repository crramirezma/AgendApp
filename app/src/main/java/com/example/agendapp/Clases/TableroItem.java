package com.example.agendapp.Clases;

import android.graphics.Bitmap;
import android.net.Uri;

public class TableroItem {
    private String nombre;
    private Bitmap imagen;
    public TableroItem(String nombre, Bitmap imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }
    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public Bitmap getImagen() {
        return imagen;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

}
