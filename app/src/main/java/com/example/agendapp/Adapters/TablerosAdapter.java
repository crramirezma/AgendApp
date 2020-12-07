package com.example.agendapp.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.agendapp.Clases.TableroItem;
import com.example.agendapp.Login.SesionActual;
import com.example.agendapp.Menu.Botones.BotonesDialog;
import com.example.agendapp.Menu.ui.Asignaturas.configAsignatura;
import com.example.agendapp.Menu.ui.Asignaturas.subCarpetas.SubtemaActivity;
import com.example.agendapp.R;
import com.example.agendapp.Tablero.MyCanvas;
import com.example.agendapp.Tablero.Tablero;
import com.example.agendapp.Tablero.TableroActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.List;

public class TablerosAdapter extends RecyclerView.Adapter<TablerosAdapter.Tablerosholder> {

    private List<TableroItem> TablerosLista;
    private Context context;


    public void refresh(){
        this.notifyDataSetChanged();
    }
    public TablerosAdapter(List<TableroItem> tablerosLista, Context context) {
        TablerosLista = tablerosLista;
        this.context = context;

    }

    @NonNull
    @Override
    public Tablerosholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tableroitem, parent, false);

        return new Tablerosholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Tablerosholder holder, int position) {

        holder.posicion = position;
        Bitmap imagen = TablerosLista.get(position).getImagen();
        String nombre = TablerosLista.get(position).getNombre();
        holder.setData(imagen, nombre);
    }

    @Override
    public int getItemCount() {
        return TablerosLista.size();
    }

    class Tablerosholder extends RecyclerView.ViewHolder{

        public int posicion;
        private ImageButton imagen;
        private TextView nombre;
        private TextView subtema;
        public Tablerosholder(@NonNull View itemView) {

            super(itemView);

            imagen = itemView.findViewById(R.id.imagenTablero);
            nombre = itemView.findViewById(R.id.nombreTablero);

            imagen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mostrarOpciones();
                }
            });
        }

        public void setData(Bitmap imagen, String nombre) {
            this.imagen.setImageBitmap(imagen);
            this.nombre.setText(nombre);
        }




        public void mostrarOpciones(){
            final String abrir="Abrir";
            final String eliminar="Eliminar";
            final String cancelar="Cancelar";

            final CharSequence[] opcion;



            //con el boolenao "bloq" se evalua si la asignatura se encuentra bloqueada o no
                opcion=new String[3];
                opcion[0]=abrir;
                opcion[1]=eliminar;
                opcion[2]=cancelar;




            final CharSequence[] opciones=opcion;
            final AlertDialog.Builder builder=new AlertDialog.Builder(context);
            builder.setTitle("Escoge una accion");
            builder.setItems(opciones, new DialogInterface.OnClickListener()  {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(opciones[which].equals(cancelar)){
                        dialog.dismiss();
                    }else if(opciones[which].equals(eliminar)){
                        eliminar(posicion);
                    }else if(opciones[which].equals(abrir)) {
                        abrir(nombre.getText().toString());
                    }
                }

            });
            builder.show();
        }

        public void eliminar(final int posicion){
            RequestQueue rq;
            JsonRequest jrq;

            final String nombre= SesionActual.subtema.getTableros().get(posicion).getNombre();
            String url="http://agendapp.atwebpages.com/Asignaturas/Subtemas/eliminarTablero.php?nombre="+nombre;

            jrq=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    boolean v;
                    try {
                        JSONArray json = response.optJSONArray("eliminado");
                        v = json.getBoolean(0);
                        if(v) {
                            SesionActual.subtema.getTableros().remove(posicion);
                            removerTablero(nombre);
                            refresh();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context,"Error eliminando el tabnlero"+error.toString(),Toast.LENGTH_SHORT).show();
                }
            });
            rq = Volley.newRequestQueue (context);
            rq.add(jrq);
        }


    }
    public void abrir(String nombre){
        Tablero.nombreTablero = nombre;
        Intent intent;
        intent=new Intent(context, Tablero.class);
        context.startActivity(intent);

    }
    public void removerTablero(String nombre){
        File root = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        File tableros = new File(root,  "/Tableros/");
        tableros.mkdirs();
        File imagen = new File(tableros,  nombre + ".jpg");

        if(imagen.exists()){
            if(imagen.delete()){
                Toast.makeText(context,"Imagen eliminada ^o^/",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(context,"La imagen no fue eliminada >:|",Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(context,"La imagen no existe ^x^/",Toast.LENGTH_SHORT).show();
        }
    }



}
