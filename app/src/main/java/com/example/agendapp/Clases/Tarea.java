package com.example.agendapp.Clases;

import com.example.agendapp.Login.SesionActual;

public class Tarea {
    private int id;
    private String nombreTarea;
    private int estadoTarea;

    
    // 1 : Tarea Pendiente
    // 2 : Tarea en Proceso
    // 3 : Tarea completada

    //cadenas que representan las opciones de estados
    public static final String pendiente="Pendiente";
    public static final String proceso="En proceso";
    public static final String completado="Tarea completada";

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //dada una opcione, la funcion getEstado retornara el la cadena especifica a la opción
    public static String getEstado(int opcion){
        String estado;
        switch (opcion){
            default:
                estado=pendiente;
                break;
            case 2:
                estado= proceso;
                break;
            case 3:
                estado=completado;
        }
        return estado;
    }
    //funcion reversa a la anterior
    public static int getNumEstado(String opcion){
        int estado;
        switch (opcion){
            default:
                estado=1;
                break;
            case proceso:
                estado= 2;
                break;
            case completado:
                estado=3;
        }
        return estado;
    }
}
