package com.example.agendapp.Menu.ui.Asignaturas.subCarpetas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
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
import com.example.agendapp.Clases.Subtema;
import com.example.agendapp.Login.SesionActual;
import com.example.agendapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SubtemaDialog extends AppCompatDialogFragment {
    RequestQueue rq;
    JsonRequest jrq;

    Context context;

    int posicion;
    EditText nombre;
    public SubtemaDialog(Context con){
        context=con;
    }


    public SubtemaDialog(int posicion,Context con){
        this.posicion=posicion;
        context=con;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view =inflater.inflate(R.layout.new_subtema_layout,null);
        builder.setView(view)
                .setTitle("Nuevo subtema")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nom=nombre.getText().toString();
                        int cred=0;

                        boolean v=true;



                            subir(nom);


                    }
                });


        nombre=view.findViewById(R.id.nuevoNombreSubtemaTxt);

        return builder.create();
    }


    public void subir(String nombre){
        String url="http://agendapp.atwebpages.com/Asignaturas/Subtemas/subirSubtema.php?asignaturaId="+SesionActual.asignatura.getId()+"&nombreSubtema="+nombre;
        url=url.replace(" ","%20");

        final String nom=nombre;
        jrq=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                int id;
                try {
                    JSONArray json = response.optJSONArray("id");


                    id = json.getInt(0);
                    Subtema subtema=new Subtema(nom);
                    subtema.setId(id);

                    SesionActual.asignatura.addSubtema(subtema);
                    Toast.makeText(context,"Salga y entre de nuevo al apartado de Subtemas para observar los cambios",Toast.LENGTH_SHORT).show();

                    /*Asignatura a=SesionActual.usuarioActual.getAsignaturas().get(posicion);
                    SesionActual.usuarioActual.setAsignatura(posicion,a);*/
                } catch (JSONException e) {
                    Toast.makeText(context,e.toString(),Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Error subiendo el subtema a la base de datos, porfavor no agregar nada nuevo al nuevo subtema"+error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        rq = Volley.newRequestQueue (context);
        rq.add(jrq);
    }
}
