package com.example.agendapp.Login;

import com.example.agendapp.Clases.Asignatura;
import com.example.agendapp.Clases.Subtema;
import com.example.agendapp.Clases.Usuario;

import java.util.ArrayList;

public class SesionActual {
    //este sera el usuario que inicio sesión en la aplicacion
    public static Usuario usuarioActual;

    //Esta sera la asignatura con la que se carguen los datos en subtema
    public static Asignatura asignatura;

    //Esta sera la posición de la Tarea que se piensa modificar
    public static int posTarea=-1;
    public static Subtema subtema;
}
