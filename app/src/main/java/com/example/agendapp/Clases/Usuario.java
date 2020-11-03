package com.example.agendapp.Clases;

import java.util.ArrayList;

public class Usuario {
    private String usuario;
    private int contraseña;
    private String Nombre;
    private String apellido;
    private String carrera;
    private int edad;
    private String ciudad;


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
        return Nombre;
    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCarrera() {
        return carrera;
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
        return ciudad;
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
}
