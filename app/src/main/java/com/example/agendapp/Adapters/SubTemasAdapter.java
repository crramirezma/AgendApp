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
import androidx.recyclerview.widget.RecyclerView;

import com.example.agendapp.Login.SesionActual;
import com.example.agendapp.R;





public class SubTemasAdapter extends RecyclerView.Adapter<SubTemasAdapter.SubtemasHolder>  {

    Context context;
    Activity activity;

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
        //

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

    }

    private void mostrarOpciones(){
        final CharSequence[] opciones={"Eliminar","Desplegar tablero","Cancelar"};
        final AlertDialog.Builder builder=new AlertDialog.Builder(activity);



        builder.setTitle("Escoge una accion");
        builder.setItems(opciones, new DialogInterface.OnClickListener()  {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(opciones[which].equals("Cancelar")){
                    dialog.dismiss();
                }else if(opciones[which].equals("Desplegar tablero")){
                    Toast.makeText(context,"Descargarndo... espere un momento",Toast.LENGTH_SHORT).show();

                    /**Zona para desplegar el tablero
                     *
                     */




                }else if(opciones[which].equals("Eliminar")) {
                    Toast.makeText(context, "Eliminar", Toast.LENGTH_SHORT).show();
                }else{
                    dialog.dismiss();
                }
            }

        });

        builder.show();
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





        }catch(IndexOutOfBoundsException e){
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public int getItemCount() {
        return SesionActual.asignatura.getSubtemas().size();
    }
}
