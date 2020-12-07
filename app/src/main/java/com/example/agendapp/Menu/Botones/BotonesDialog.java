package com.example.agendapp.Menu.Botones;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.agendapp.Clases.Asignatura;
import com.example.agendapp.Login.SesionActual;
import com.example.agendapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BotonesDialog  extends AppCompatDialogFragment {

    RequestQueue rq;
    JsonRequest jrq;

    Context context;
    int posicion;
    boolean decision;
    Adapter adapter;

    ImageButton opcion1;
    ImageButton opcion2;
    ImageButton opcion3;
    ImageButton opcion4;
    ImageButton opcion5;
    ImageButton opcion6;
    ImageButton opcion7;
    ImageButton opcion8;
    ImageButton opcion9;


    /**
     *
     * @param posicion: posición dentro del arrayList que contiene al subtema o asignatura
     * @param con: contexto, necesario para el paso de mensaje y otras cosas
     * @param des: Servira para definir si es cambio de boton para subtemas o para asignaturas
     *           true: asignaturas
     *           false: subtemas
     */
    public BotonesDialog(int posicion,Context con,boolean des,Adapter adapter){
        this.posicion=posicion;
        context=con;
        this.decision=des;
        this.adapter=adapter;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view =inflater.inflate(R.layout.botones_layout,null);
        builder.setView(view)
                .setTitle("Escoge un boton")
                .setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                ;


        opcion1=view.findViewById(R.id.opcion1);
        opcion2=view.findViewById(R.id.opcion2);
        opcion3=view.findViewById(R.id.opcion3);
        opcion4=view.findViewById(R.id.opcion4);
        opcion5=view.findViewById(R.id.opcion5);
        opcion6=view.findViewById(R.id.opcion6);
        opcion7=view.findViewById(R.id.opcion7);
        opcion8=view.findViewById(R.id.opcion8);
        opcion9=view.findViewById(R.id.opcion9);

        int id;
        if(decision){
            id=SesionActual.usuarioActual.getAsignaturas().get(posicion).getId();
        }else{
            id=SesionActual.asignatura.getSubtemas().get(posicion).getId();
        }
        final int idAsignatura=id;
        opcion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiar(1,idAsignatura);
            }
        });
        opcion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiar(2,idAsignatura);
            }
        });
        opcion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiar(3,idAsignatura);
            }
        });
        opcion4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiar(4,idAsignatura);
            }
        });
        opcion5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiar(5,idAsignatura);
            }
        });
        opcion6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiar(6,idAsignatura);
            }
        });
        opcion7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiar(7,idAsignatura);
            }
        });
        opcion8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiar(8,idAsignatura);
            }
        });
        opcion9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiar(9,idAsignatura);
            }
        });




        return builder.create();
    }


    public void cambiar(int cambio,int idAsignatura){
        if(decision)
            cambiarA(cambio,idAsignatura);
        else
            cambiarS(cambio,idAsignatura);

    }
    public void cambiarA(int cambio,int idAsignatura){
        String url="http://agendapp.atwebpages.com/Asignaturas/cambiarIcono.php?imagen="+cambio+"&idAsignatura="+idAsignatura;
        url=url.replace(" ","%20");

        final int camb=cambio;
        jrq=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                boolean v;
                try {
                    JSONArray json = response.optJSONArray("cambio");


                    v = json.getBoolean(0);
                    SesionActual.usuarioActual.getAsignaturas().get(posicion).setImagen(camb);

                    //Toast.makeText(context,"Salga y entre de nuevo al apartado de Asignaturas para observar los cambios",Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();

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

    public void cambiarS(int cambio,int idSubtema){

        String url="http://agendapp.atwebpages.com/Asignaturas/Subtemas/cambiarIconoS.php?imagen="+cambio+"&idSubtema="+idSubtema;
        url=url.replace(" ","%20");

        final int camb=cambio;
        jrq=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                boolean v;
                try {
                    JSONArray json = response.optJSONArray("cambio");


                    v = json.getBoolean(0);
                    SesionActual.asignatura.getSubtemas().get(posicion).setIcono(camb);
                    

                    //Toast.makeText(context,"Salga y entre de nuevo al apartado de Subtemas para observar los cambios",Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Error cambiando el icono al subtema\n"+error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        rq = Volley.newRequestQueue (context);
        rq.add(jrq);
    }
}
