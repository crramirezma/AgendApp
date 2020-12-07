package com.example.agendapp.Clases;

import java.util.ArrayList;
import java.util.List;

public class Subtema {
    private Asignatura asignaturaSubtema;
    private String nombreSubtema;
    private int id;
    private int icono;
    private List<TableroItem> tableros = new ArrayList<>();

    public Asignatura getAsignaturaSubtema() {
        return asignaturaSubtema;
    }

    public int getIcono() {
        return icono;
    }

    public void setIcono(int icono) {
        this.icono = icono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Subtema(String nombreSubtema, Asignatura asignaturaSubtema) {
        this.asignaturaSubtema=asignaturaSubtema;
        this.nombreSubtema=nombreSubtema;
    }

    public Subtema(String nombreSubtema) {
        this.nombreSubtema = nombreSubtema;
    }

    public String getNombreSubtema() {
        return nombreSubtema;
    }

    public void setNombreSubtema(String nombreSubtema) {
        this.nombreSubtema= nombreSubtema;
    }

    public Asignatura getasignaturaSubtema() {
        return asignaturaSubtema;
    }

    public void setAsignaturaSubtema(Asignatura asignaturaSubtema) {
        this.asignaturaSubtema= asignaturaSubtema;
    }

    public List<TableroItem> getTableros() {
        return tableros;
    }

    public void setTableros(List<TableroItem> tableros) {
        this.tableros = tableros;
    }

    public void addTablero(TableroItem tablero){
        this.tableros.add(tablero);
    }
}
