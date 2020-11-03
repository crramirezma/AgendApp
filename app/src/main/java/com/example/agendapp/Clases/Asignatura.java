package com.example.agendapp.Clases;

import java.util.ArrayList;

public class Asignatura {
    int id;


    private ArrayList<Subtema> subtemas=new ArrayList<>();
    private String nombre;
    private int creditos;
    private double tiempoEstudio;

    public Asignatura(String nombre, int creditos, double tiempoEstudio) {
        this(nombre, creditos);
        this.tiempoEstudio=tiempoEstudio;
    }

    public Asignatura(String nombre,int creditos){
        this.nombre=nombre;
        this.creditos=creditos;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public ArrayList<Subtema> getsubtemas() {
        return subtemas;
    }
    public void setSubtemas(ArrayList<Subtema> subtemas) {
        this.subtemas = subtemas;
    }
    public void addSubtema(Subtema subtema){
        subtemas.add(subtema);
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public double getTiempoEstudio() {
        return tiempoEstudio;
    }

    public void setTiempoEstudio(double tiempoEstudio) {
        this.tiempoEstudio = tiempoEstudio;
    }
}
