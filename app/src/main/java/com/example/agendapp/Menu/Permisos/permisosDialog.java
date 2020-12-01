package com.example.agendapp.Menu.Permisos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.agendapp.Login.SesionActual;
import com.example.agendapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class permisosDialog extends AppCompatDialogFragment {
    private EditText password;
    Context context;


    int decision;
    int posicion;


    /**
     *
     * @param decision
     * Con esta variable se le dice al dialogo de "permisos" cual acción ejecutar
     * 11:eliminar asignatura
     * 12: bloquear la asignatura
     * 13: desbloquearla
     *
     * 31: eliminar tarea
     */
    public permisosDialog(int decision, int pos,Context context){
        this.decision=decision;
        this.posicion=pos;
        this.context=context;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view =inflater.inflate(R.layout.pedir_contrasena,null);

        builder.setView(view)
                .setTitle("Pidiendo permisos")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int contrasena=Integer.parseInt(password.getText().toString());


                        if(contrasena==SesionActual.usuarioActual.getContraseña()){

                            switch (decision){
                                //caso de eliminado de asignatura
                                case 11:
                                    eliminarAsignatura(posicion);
                                    break;
                                //caso de bloqueo de asignatura
                                case 12:
                                    bloquearAsignatura(1,posicion);
                                    break;
                                //desbloqueo de asignatura
                                case 13:
                                    bloquearAsignatura(2,posicion);
                                    break;

                                case 31:
                                    eliminarTarea(posicion);
                                    break;
                            }
                        }else{
                            dialog.dismiss();
                        }

                    }
                });

        password=view.findViewById(R.id.contrasenaET);

        return builder.create();
    }

    /**
     * Zona de Eliminado de asignaturas
     *
     * eliminarAsignatura
     *
     * @param pos: entero que representa la posición de la asignatura dentro del arraylist, se usa para poder haller el dato
     *
     * la función se usa para desplegar un mensaje de advertencia antes de proseguir con el eliminado de la asignatura
     *
     */
        public  void crearMensaje(int pos){

    }


    public void eliminarAsignatura(int pos){
        AlertDialog.Builder alerta=new AlertDialog.Builder(context);
        final int posicion=pos;
        String asignatura=SesionActual.usuarioActual.getAsignaturas().get(pos).getNombre();
        alerta
                .setMessage("Esta seguro de eliminar la asignatura '"+asignatura+"' definitivamente, tenga en cuenta que seran borrados todos los subtemas asociados a la asignatura.")
                .setTitle("Eliminando asignatura")
                .setPositiveButton("si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //programar eliminación de la asignatura

                        eliminarAsignatura2(posicion);
                    }
                })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alerta.show();
    }
    public void eliminarAsignatura2(int pos){
        //Variables para la consulta json
        RequestQueue rq;
        JsonRequest jrq;

        int id= SesionActual.usuarioActual.getAsignaturas().get(pos).getId();
        String url="http://agendapp.atwebpages.com/Asignaturas/eliminarAsignatura.php?idA="+id;
        final int posicion=pos;

        jrq=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                boolean v;
                try {
                    JSONArray json = response.optJSONArray("cambio");
                    v = json.getBoolean(0);
                    Toast.makeText(context,v+"",Toast.LENGTH_SHORT).show();
                    if(v)
                        SesionActual.usuarioActual.getAsignaturas().remove(posicion);

                    Toast.makeText(context,"Salga y entre de nuevo al apartado de Asignaturas para observar los cambios",Toast.LENGTH_SHORT).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Error cambiando el icono a la asignatura"+error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        rq = Volley.newRequestQueue (context);
        rq.add(jrq);

    }



    public void bloquearAsignatura(int v, int posicion){
        //Variables para la consulta json
        RequestQueue rq;
        JsonRequest jrq;

        int id=SesionActual.usuarioActual.getAsignaturas().get(posicion).getId();
        String url="http://agendapp.atwebpages.com/Asignaturas/bloquearAsignautra.php?bloq="+v+"&idA="+id;
        url=url.replace(" ","%20");

        final int pos=posicion;
        final boolean camb=v==1;

        jrq=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                boolean v;
                try {
                    JSONArray json = response.optJSONArray("cambio");


                    v = json.getBoolean(0);
                    SesionActual.usuarioActual.getAsignaturas().get(pos).setBloqueado(camb);

                    Toast.makeText(context,"Salga y entre de nuevo al apartado de Asignaturas para observar los cambios",Toast.LENGTH_SHORT).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Error cambiando el estado a la asignatura"+error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        rq = Volley.newRequestQueue (context);
        rq.add(jrq);
    }
    public void eliminarTarea(int posicion){
        RequestQueue rq;
        JsonRequest jrq;

        int id=SesionActual.usuarioActual.getTareas().get(posicion).getId();
        String url="http://agendapp.atwebpages.com/Tareas/eliminarTarea.php?idTarea="+id;
        url=url.replace(" ","%20");

        final int pos=posicion;


        jrq=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                boolean v;
                try {
                    JSONArray json = response.optJSONArray("cambio");


                    v = json.getBoolean(0);

                    if(v)
                        SesionActual.usuarioActual.getTareas().remove(pos);

                    Toast.makeText(context,"Salga y entre de nuevo al apartado de Tareas para observar los cambios",Toast.LENGTH_SHORT).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Error eliminando la tarea"+error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        rq = Volley.newRequestQueue (context);
        rq.add(jrq);
    }

}
