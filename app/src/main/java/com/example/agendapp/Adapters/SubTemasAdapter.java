package com.example.agendapp.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agendapp.Login.SesionActual;
import com.example.agendapp.Menu.Botones.BotonesDialog;
import com.example.agendapp.R;





public class SubTemasAdapter extends RecyclerView.Adapter<SubTemasAdapter.SubtemasHolder>  {

    Context context;
    Activity activity;
    public FragmentManager manager;

    //TextView
    TextView id;


    public SubTemasAdapter (Context context, Activity activity){

        this.context=context;
        this.activity=activity;
    }



    public class SubtemasHolder extends RecyclerView.ViewHolder {
        TextView nombreSubtema;
        TextView Numero;

        //Buttons
        ImageButton subtemaBt;


        Context context;



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




                    }else if(opciones[which].equals(eliminar)) {
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




        }catch(IndexOutOfBoundsException e){
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public int getItemCount() {
        return SesionActual.asignatura.getSubtemas().size();
    }
}
