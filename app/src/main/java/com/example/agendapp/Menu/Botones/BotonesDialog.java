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


    ImageButton opcion1;
    ImageButton opcion2;
    ImageButton opcion3;
    ImageButton opcion4;
    ImageButton opcion5;
    ImageButton opcion6;
    ImageButton opcion7;
    ImageButton opcion8;
    ImageButton opcion9;



    public BotonesDialog(int posicion,Context con){
        this.posicion=posicion;
        context=con;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view =inflater.inflate(R.layout.botones_layout,null);
        builder.setView(view)
                .setTitle("Nueva asignatura")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
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



        return builder.create();
    }


    public void subir(String nombre,int creditos){
        String url="http://agendapp.atwebpages.com/Asignaturas/subirAsignatura.php?asignaturaN="+nombre+"&usuarioN="+ SesionActual.usuarioActual.getUsuario()+"&creditos="+creditos;
        url=url.replace(" ","%20");

        final String nom=nombre;
        final int cred=creditos;
        jrq=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                int id;
                try {
                    JSONArray json = response.optJSONArray("id");


                    id = json.getInt(0);
                    Asignatura a=new Asignatura(nom,cred);
                    a.setId(id);
                    SesionActual.usuarioActual.addAsignatura(a);
                    Toast.makeText(context,"Salga y entre de nuevo al apartado de Asignaturas para observar los cambios",Toast.LENGTH_SHORT).show();

                    /*Asignatura a=SesionActual.usuarioActual.getAsignaturas().get(posicion);
                    SesionActual.usuarioActual.setAsignatura(posicion,a);*/
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Error subiendo la asignatura a la base de datos, porfavor no agregar nada nuevo a la nueva asignatura"+error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        rq = Volley.newRequestQueue (context);
        rq.add(jrq);
    }
}
