package com.example.agendapp.Clases;

public class Asignatura {
    private String nombre;
    private int creditos;
    private double tiempoEstudio;

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
