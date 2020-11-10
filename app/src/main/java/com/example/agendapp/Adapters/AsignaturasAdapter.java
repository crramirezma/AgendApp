package com.example.agendapp.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
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
import com.example.agendapp.Login.SesionActual;
import com.example.agendapp.Menu.Botones.BotonesDialog;
import com.example.agendapp.Menu.ui.Asignaturas.AsignaturaDialog;
import com.example.agendapp.Menu.ui.Asignaturas.subCarpetas.SubtemaActivity;
import com.example.agendapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AsignaturasAdapter extends RecyclerView.Adapter<AsignaturasAdapter.asignaturasHolder>{
    public Context context;
    public Fragment asignatura;
    public FragmentManager manager;


    public AsignaturasAdapter(Context context){
        this.context=context;
    }

    @NonNull
    @Override
    public asignaturasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.asignatura_fragment,null,false);
        /*RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);*/
        return new asignaturasHolder(view,context);

    }

    @Override
    public void onBindViewHolder(@NonNull asignaturasHolder holder, int position) {
        try{

            holder.asignarDatos(position);

        }catch (IndexOutOfBoundsException e){

        }
    }

    @Override
    public int getItemCount() {
        return SesionActual.usuarioActual.getAsignaturas().size();
    }




    public class asignaturasHolder extends RecyclerView.ViewHolder{
        //Variables para la consulta json
        RequestQueue rq;
        JsonRequest jrq;

        TextView asignaturaNombre;
        ImageButton btAsignatura;
        TextView NumeroTxt;

        public Context context;

        public asignaturasHolder(@NonNull View itemView,Context context) {
            super(itemView);
            this.context=context;
            asignaturaNombre=itemView.findViewById(R.id.asignaturaTxt);
            btAsignatura=itemView.findViewById(R.id.asignaturaBt);
            NumeroTxt=itemView.findViewById(R.id.NumeroTxt);



            final Context context2=context;

            btAsignatura.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mostrarOpciones(NumeroTxt.getText().toString());
                }
            });




        }

        public void asignarDatos(int i){
            asignaturaNombre.setText(SesionActual.usuarioActual.getAsignaturas().get(i).getNombre());
            NumeroTxt.setText(i+"");
            //cambiando la imagen del icono


            asignarIcono(SesionActual.usuarioActual.getAsignaturas().get(i).getImagen());
        }

        public void abrirDialogo(int i){
            int pos;
            try{
                pos=Integer.parseInt(NumeroTxt.getText().toString());
                AsignaturaDialog asignaturaDialog=new AsignaturaDialog(pos,context);
                asignaturaDialog.show(((AppCompatActivity) context).getSupportFragmentManager(),"Nuevo Dialogo");
            }catch (Exception e){
                Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();
            }

        }


        public void mostrarOpciones(String posicion){
            final String subtemas="Lista de Subtemas";
            final String eliminar="Eliminar Asignatura";
            String modificar="Modificar Asignatura";
            final String icono="Cambiar Icono";
            final String cancel="Cancelar";


            final CharSequence[] opciones={subtemas,eliminar,modificar,icono,cancel};
            final AlertDialog.Builder builder=new AlertDialog.Builder(context);
            final int pos=Integer.parseInt(posicion);


            builder.setTitle("Escoge una accion");
            builder.setItems(opciones, new DialogInterface.OnClickListener()  {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(opciones[which].equals(cancel)){
                        dialog.dismiss();
                    }else if(opciones[which].equals(eliminar)){
                        Toast.makeText(context,"Eliminar",Toast.LENGTH_SHORT).show();
                    }else if(opciones[which].equals(icono)){
                        BotonesDialog botonesDialog=new BotonesDialog(pos,context);
                        botonesDialog.show(manager,"Escoge un nuevo icono");
                    }else if(opciones[which].equals(subtemas)){

                        SesionActual.asignatura= SesionActual.usuarioActual.getAsignaturas().get(Integer.parseInt(NumeroTxt.getText().toString()));
                        llenarSubtemas();




                    }
                }

            });
            builder.show();
        }


        public  void asignarIcono(int i){
            switch (i){
                case 1:
                    btAsignatura.setImageResource(R.drawable.opcion_1);
                    break;
                case 2:
                    btAsignatura.setImageResource(R.drawable.opcion_2);
                    break;
                case 3:
                    btAsignatura.setImageResource(R.drawable.opcion_3);
                    break;
                case 4:
                    btAsignatura.setImageResource(R.drawable.opcion_4);
                    break;
                case 5:
                    btAsignatura.setImageResource(R.drawable.opcion_5);
                    break;
                case 6:
                    btAsignatura.setImageResource(R.drawable.opcion_6);
                    break;
                case 7:
                    btAsignatura.setImageResource(R.drawable.opcion_7);
                    break;
                case 8:
                    btAsignatura.setImageResource(R.drawable.opcion_8);
                    break;
                case 9:
                    btAsignatura.setImageResource(R.drawable.opcion_9);
                    break;
                default:
                    btAsignatura.setImageResource(R.drawable.opcion_1);

            }

        }

        public void llenarSubtemas(){
            //aqui se hara la consulta a la base de datos de los subtemas de la asignatura

            String url="http://agendapp.atwebpages.com/Asignaturas/listarSubtemas.php?idA="+SesionActual.asignatura.getId();

            url=url.replace(" ","%20");
            jrq=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Subtema subtema;


                    JSONArray json;
                    int id;


                    String nombreSubtema;

                    double tiempo;

                    JSONObject jsonObject;


                    json=response.optJSONArray("subtemas");

                    try {
                        for(int i=0;i<json.length();i++){




                            jsonObject=json.getJSONObject(i);
                            id=jsonObject.optInt("id");

                            nombreSubtema=jsonObject.optString("nombre");

                            subtema=new Subtema(nombreSubtema);
                            subtema.setId(id);

                            SesionActual.asignatura.addSubtema(subtema);


                        }




                        //Zona de Intents y nuevas vistas
                        /* Gracias a que se declaran justo en el hilo donde se ejecuta la busqueda, la nueva vista vendra crgada y sincronizada con los datos del valor estatico*/
                        Toast.makeText(context,SesionActual.asignatura.getId()+"",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(context, SubtemaActivity.class);
                        context.startActivity(intent);



                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(context,e.toString(),Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText ( context,"No tienes subtemas programadas",Toast.LENGTH_SHORT).show ();
                    Intent intent=new Intent(context, SubtemaActivity.class);
                    context.startActivity(intent);

                }
            });
            rq = Volley.newRequestQueue (context);
            rq.add(jrq);


        }
    }
}
