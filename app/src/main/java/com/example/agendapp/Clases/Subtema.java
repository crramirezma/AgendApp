package com.example.agendapp.Clases;

public class Subtema {
    private Asignatura asignaturaSubtema;
    private String nombreSubtema;
    private int id;



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

}