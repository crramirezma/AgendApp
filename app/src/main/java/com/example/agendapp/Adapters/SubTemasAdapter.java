package com.example.agendapp.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.agendapp.Clases.Subtema;
import com.example.agendapp.Clases.TableroItem;
import com.example.agendapp.Login.SesionActual;
import com.example.agendapp.Menu.Botones.BotonesDialog;
import com.example.agendapp.Menu.ui.Asignaturas.configAsignatura;
import com.example.agendapp.Menu.ui.Asignaturas.subCarpetas.SubtemaActivity;
import com.example.agendapp.Menu.Permisos.permisosDialog;
import com.example.agendapp.R;
import com.example.agendapp.Tablero.TableroActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;


public class SubTemasAdapter extends RecyclerView.Adapter<SubTemasAdapter.SubtemasHolder>  {


    Context context;
    Activity activity;
    public FragmentManager manager;

    //TextView
    TextView id;

    public void refresh(){
        this.notifyDataSetChanged();
    }

    public SubTemasAdapter (Context context, Activity activity){
        this.context=context;
        this.activity=activity;

    }

    @NonNull
    @Override
    public SubtemasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_subtema,parent,false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                ,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new SubtemasHolder(vista,context);
    }

    @Override
    public void onBindViewHolder(@NonNull SubtemasHolder holder, int position) {
        try{
            holder.nombreSubtema.setText( SesionActual.asignatura.getSubtemas().get(position).getNombreSubtema());
            holder.Numero.setText(position+"");
            holder.subtemaBt.setImageResource(R.drawable.opcion_1);
            holder.asignarIcono(SesionActual.asignatura.getSubtemas().get(position).getIcono());
            holder.posicion=position;
            holder.adapter=this;

        }catch(IndexOutOfBoundsException e){
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public int getItemCount() {
        return SesionActual.asignatura.getSubtemas().size();
    }

    public class SubtemasHolder extends RecyclerView.ViewHolder {

        JsonRequest jrq;
        RequestQueue rq;
        TextView nombreSubtema;
        TextView Numero;

        //Buttons
        ImageButton subtemaBt;

        int posicion;

        Context context;
        RecyclerView.Adapter adapter;



        public SubtemasHolder(View itemView, Context context){

            super(itemView);
            this.context=context;
            nombreSubtema=itemView.findViewById(R.id.nombreSubtema);
            Numero=itemView.findViewById(R.id.NumeroTxt);

            subtemaBt=itemView.findViewById(R.id.subtemaBt);

            subtemaBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mostrarOpciones();
                }
            });
        }
        private void mostrarOpciones(){

            AlertDialog.Builder builder=new AlertDialog.Builder(activity);
            final String eliminar="Eliminar";
            final String desplegar="Desplegar tablero";
            final String cancelar="Cancelar";
            final String cambiarI="Cambiar Icono";
            final CharSequence[] opciones={desplegar,cambiarI,eliminar,cancelar};

            final int pos=posicion;
            builder.setTitle("Escoge una accion");
            builder.setItems(opciones, new DialogInterface.OnClickListener()  {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(opciones[which].equals(cancelar)){
                        dialog.dismiss();
                    }else if(opciones[which].equals(desplegar)){
                        Toast.makeText(context,"Descargarndo... espere un momento",Toast.LENGTH_SHORT).show();

                        /**Zona para desplegar el tablero
                         *
                         */
                        SesionActual.subtema = SesionActual.asignatura.getSubtemas().get(Integer.parseInt(Numero.getText().toString()));
                        if(SesionActual.subtema.getTableros().size()==0) {
                            llenarTableros();
                        }
                        else{
                            Intent intent=new Intent(context, TableroActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }


                    }else if(opciones[which].equals(eliminar)) {
                        permiso(21,pos);
                        Toast.makeText(context, "Eliminar", Toast.LENGTH_SHORT).show();
                    }else if(opciones[which].equals(cambiarI)){
                        try{
                            int pos=Integer.parseInt(Numero.getText().toString());
                            BotonesDialog botonesDialog=new BotonesDialog(pos,context,false);
                            botonesDialog.show(manager,"Escoge un nuevo icono");
                        }catch (Exception e){
                            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(context,"Error con la contraseña, intentelo de nuevo",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }

            });

            builder.show();
        }

        public void asignarIcono(int i){
            switch (i){
                case 1:
                    subtemaBt.setImageResource(R.drawable.opcion_1);
                    break;
                case 2:
                    subtemaBt.setImageResource(R.drawable.opcion_2);
                    break;
                case 3:
                    subtemaBt.setImageResource(R.drawable.opcion_3);
                    break;
                case 4:
                    subtemaBt.setImageResource(R.drawable.opcion_4);
                    break;
                case 5:
                    subtemaBt.setImageResource(R.drawable.opcion_5);
                    break;
                case 6:
                    subtemaBt.setImageResource(R.drawable.opcion_6);
                    break;
                case 7:
                    subtemaBt.setImageResource(R.drawable.opcion_7);
                    break;
                case 8:
                    subtemaBt.setImageResource(R.drawable.opcion_8);
                    break;
                case 9:
                    subtemaBt.setImageResource(R.drawable.opcion_9);
                    break;
                default:
                    subtemaBt.setImageResource(R.drawable.opcion_1);

            }
        }
        public void  permiso(int decision, int pos){
            permisosDialog permisosDialog=new permisosDialog(decision,pos,context,adapter);
            permisosDialog.show(manager,"Permisos");
        }


        public void llenarTableros(){
            //aqui se hara la consulta a la base de datos de los subtemas de la asignatura

            String url="http://agendapp.atwebpages.com/Asignaturas/Subtemas/listarTableros.php?idS="+SesionActual.subtema.getId();
            url=url.replace(" ","%20");
            jrq=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    TableroItem tablero;

                    JSONArray json;
                    int id;
                    int icono;

                    String nombreTablero;

                    double tiempo;

                    JSONObject jsonObject;
                    System.out.println("Con: "+ SesionActual.subtema.getId()+"JSON: "+response.toString());

                    json=response.optJSONArray("tableros");


                    try {
                        for(int i=0;i<json.length();i++){

                            jsonObject=json.getJSONObject(i);
                            id=jsonObject.optInt("id");
                            nombreTablero=jsonObject.optString("nombre");

                            tablero=new TableroItem(nombreTablero);
                            tablero.setId(id);

                            //Agregar
                            SesionActual.subtema.addTablero(tablero);


                        }

                        //Zona de Intents y nuevas vistas
                        /* Gracias a que se declaran justo en el hilo donde se ejecuta la busqueda, la nueva vista vendra crgada y sincronizada con los datos del valor estatico*/

                        Intent intent;
                        intent=new Intent(context, TableroActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);






                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(context,e.toString(),Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context,"No tienes Tableros en este subtema",Toast.LENGTH_SHORT).show ();
                    Intent intent;
                    intent=new Intent(context, TableroActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);




                }
            });
            rq = Volley.newRequestQueue (context);
            rq.add(jrq);


        }
    }



}
