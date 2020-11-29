package com.example.agendapp.Clases;

public class Subtema {
    private Asignatura asignaturaSubtema;
    private String nombreSubtema;
    private int id;
    private int icono;

    public Asignatura getAsignaturaSubtema() {
        return asignaturaSubtema;
    }

    public int getIcono() {
        return icono;
    }

    public void setIcono(int icono) {
        this.icono = icono;
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
