package com.example.agendapp.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agendapp.Clases.Tarea;
import com.example.agendapp.Login.SesionActual;
import com.example.agendapp.Menu.Permisos.permisosDialog;
import com.example.agendapp.Menu.ui.Tareas.Datos_Tarea;
import com.example.agendapp.R;

public class TareasAdapter extends RecyclerView.Adapter<TareasAdapter.tareasHolder> {
    public Context context;
    public Fragment TareaFragment;
    public FragmentManager manager;

    public TareasAdapter(Context context){
        this.context=context;
    }
    @NonNull
    @Override
    public tareasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_tareas,null,false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        return new TareasAdapter.tareasHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull tareasHolder holder, int position) {
        holder.asignarDatos(position);
    }

    @Override
    public int getItemCount() {
        return SesionActual.usuarioActual.getTareas().size();
    }



    //###########################################################
    //                      Clase holder
    //###########################################################
    public class tareasHolder extends RecyclerView.ViewHolder{
        Context context;

        int posicion;
        //TextView NumberTxt;
        TextView nombreTxt;

        Button estadoButton;


        public tareasHolder(@NonNull View itemView,Context context) {
            super(itemView);
            this.context=context;
            //NumberTxt=itemView.findViewById(R.id.NumeroTxt);
            nombreTxt=itemView.findViewById(R.id.nombreTarea);

            estadoButton=itemView.findViewById(R.id.estadoBt);
        }

        public void asignarDatos(int position){
            posicion=position;
            nombreTxt.setText(SesionActual.usuarioActual.getTareas().get(position).getnombreTarea());
            String estado= Tarea.getEstado(SesionActual.usuarioActual.getTareas().get(position).getestadoTarea());
            estadoButton.setText(estado);

            Listener(position);
        }
        public void Listener(int position){
            final int pos=position;
            estadoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //se desplegara la lista de opciones para usar con las tareas
                    mostrarOpciones(pos);
                }
            });
        }

        //mostrarOpciones despliega una lista de opciones para el usuario
        public void mostrarOpciones(int posicion){
            final int pos=posicion;

            //lista de opciones que seran desplegadas
            final String cancelar="Cancelar";
            final String eliminar="Eliminar";
            final String modificar="Modificar tarea";
            //final String modificarEstado="Modificar estado de la tarea";

            final CharSequence[] opciones={modificar,eliminar,cancelar};

            //mostrando la lista de opciones en la pantalla por medio de un AlertDialog
            final AlertDialog.Builder builder=new AlertDialog.Builder(context);
            builder.setTitle("Escoge una acción");
            builder.setItems(opciones, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch(opciones[which].toString()){
                        case cancelar:
                            dialog.dismiss();
                            break;
                        case eliminar:
                            //Codigo necesario para eliminar una tarea
                            permiso(31,pos);
                            break;
                        case modificar:
                            //codigo para modificar tarea
                            SesionActual.posTarea=pos;
                            Intent intent=new Intent(context, Datos_Tarea.class);
                            context.startActivity(intent);
                            break;
                        //case modificarEstado:
                            //break;
                        default:
                            dialog.dismiss();
                            break;

                    }
                }
            });
            builder.show();

        }

        //para poder eliminar la Tarea, es obligatorio brindar la contraseña del usuario
        public void permiso(int decision,int pos){
            permisosDialog permisosDialog=new permisosDialog(decision,pos,context);
            permisosDialog.show(manager,"Permisos");
        }
    }
}
