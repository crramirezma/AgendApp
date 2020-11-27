package com.example.agendapp.Clases;

public class Tarea {

    private String nombreTarea;
    private int estadoTarea;

    
    // 1 : Tarea Pendiente
    // 2 : Tarea en Proceso
    // 3 : Tarea completada


    public Tarea(String nombreTarea,int estadoTarea){
        this.nombreTarea = nombreTarea;
        this.estadoTarea = estadoTarea;
    }


    public String getnombreTarea() {
        return nombreTarea;
    }


    public void setnombreTarea(String nombreTarea) {
        this.nombreTarea=nombreTarea;
    }

    public int getestadoTarea() {
        return estadoTarea;
    }


    public void setestadoTarea(int estadoTarea) {
        this.estadoTarea=estadoTarea;
    }
}
