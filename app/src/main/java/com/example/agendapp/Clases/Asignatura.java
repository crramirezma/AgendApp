package com.example.agendapp.Clases;

import java.util.ArrayList;

public class Asignatura {
    int id;
    int imagen;

    private ArrayList<Subtema> subtemas=new ArrayList<>();
    private String nombre;
    private int creditos;
    private double tiempoEstudio;


    public Asignatura(String nombre, int creditos, double tiempoEstudio, int img) {
        this(nombre, creditos,img);
        this.tiempoEstudio=tiempoEstudio;

    }

    public Asignatura(String nombre,int creditos, int img){
        this.nombre=nombre;
        this.creditos=creditos;
        this.imagen=img;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public ArrayList<Subtema> getSubtemas() {
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

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }


}
