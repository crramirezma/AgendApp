package com.example.agendapp.Clases;

import java.util.ArrayList;

public class Usuario {
    private String usuario;
    private int contraseña;
    private String Nombre;
    private String apellido;
    private String carrera;
    private int edad=0;
    private String ciudad;

    private ArrayList<Tarea> tareas =new ArrayList<>();
    private ArrayList<Asignatura> asignaturas=new ArrayList<>();



    //Este constructor se usara para el login de la aplicacion
    public Usuario(String usuario, int contraseña){
        this.contraseña=contraseña;
        this.usuario=usuario;
    }

    public Usuario(String usuario, int contraseña, String nombre, String apellido, String carrera, int edad, String ciudad) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        Nombre = nombre;
        this.apellido = apellido;
        this.carrera = carrera;
        this.edad = edad;
        this.ciudad = ciudad;
    }

    public void setUsuario(String usuario){
        this.usuario=usuario;
    }
    public void setContraseña(int contraseña){
        this.contraseña=contraseña;
    }

    public ArrayList<Asignatura> getAsignaturas() {
        return asignaturas;
    }
    public void setAsignaturas(ArrayList<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public String getUsuario(){
        return this.usuario;
    }
    public int getContraseña(){
        return this.contraseña;
    }

    public String getNombre() {

        try{
            return Nombre;
        }catch(Exception e){
            return null;
        }

    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {

        try{
            return apellido;
        }catch(Exception e){
            return null;
        }
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCarrera() {

        try{
            return carrera;
        }catch(Exception e){
            return null;
        }
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;

    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCiudad() {


        try{
            return ciudad;
        }catch(Exception e){
            return null;
        }
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }


    public void addAsignatura(Asignatura asignatura){
        asignaturas.add(asignatura);
    }

    public void setAsignatura(int posicion, Asignatura asignatura){
        asignaturas.set(posicion,asignatura);
    }

    public ArrayList<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(ArrayList<Tarea> tareas) {
        this.tareas = tareas;
    }

    public void addTarea(Tarea tarea){
        this.tareas.add(tarea);
    }
}
