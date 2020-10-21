package com.example.agendapp.Clases;

public class Usuario {
    private String usuario;
    private int contraseña;

    //Este constructor se usara para el login de la aplicacion
    public Usuario(String usuario, int contraseña){
        this.contraseña=contraseña;
        this.usuario=usuario;
    }



    public void setUsuario(String usuario){
        this.usuario=usuario;
    }
    public void setContraseña(int contraseña){
        this.contraseña=contraseña;
    }

    public String getUsuario(){
        return this.usuario;
    }
    public int getContraseña(){
        return this.contraseña;
    }
}
