package com.example.agendapp.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
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
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agendapp.Login.SesionActual;
import com.example.agendapp.Menu.Botones.BotonesDialog;
import com.example.agendapp.Menu.ui.Asignaturas.AsignaturaDialog;
import com.example.agendapp.Menu.ui.Asignaturas.subCarpetas.SubTemaFragment;
import com.example.agendapp.R;

import java.util.ArrayList;

public class AsignaturasAdapter extends RecyclerView.Adapter<AsignaturasAdapter.asignaturasHolder>{
    public Context context;
    public Fragment asignatura;


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
        TextView asignaturaNombre;
        ImageButton btAsignatura;
        TextView NumeroTxt;

        public Context context;

        public asignaturasHolder(@NonNull View itemView,Context context) {
            super(itemView);
            this.context=context;
            asignaturaNombre=itemView.findViewById(R.id.NombreAsignatura);
            btAsignatura=itemView.findViewById(R.id.btAsignatura);
            NumeroTxt=itemView.findViewById(R.id.NumeroTxt);

            final Context context2=context;
            try{

                Toast.makeText(context,NumeroTxt.getText().toString(),Toast.LENGTH_SHORT).show();
                final int pos=Integer.parseInt(NumeroTxt.getText().toString());
                btAsignatura.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(asignatura.getContext(),"",Toast.LENGTH_SHORT).show();

                        final CharSequence[] opciones={"Eliminar asignatura","Modificar Asignatura","Cambiar Icono","Cancelar"};
                        final AlertDialog.Builder builder=new AlertDialog.Builder(context2);



                        builder.setTitle("Escoge una accion");
                        builder.setItems(opciones, new DialogInterface.OnClickListener()  {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(opciones[which].equals("Cancelar")){
                                    dialog.dismiss();
                                }else if(opciones[which].equals("Descargar")){
                                    Toast.makeText(context2,"Descargarndo... espere un momento",Toast.LENGTH_SHORT).show();



                                }else if(opciones[which].equals("Eliminar")){
                                    Toast.makeText(context2,"Eliminar",Toast.LENGTH_SHORT).show();
                                }else if(opciones[which].equals("Cambiar Icono")){
                                    BotonesDialog botonesDialog=new BotonesDialog(pos,context2);
                                    botonesDialog.show(asignatura.getParentFragmentManager(),"Nuevo Dialogo");
                                }
                            }

                        });
                        builder.show();

                    }
                });
            }catch (Exception e){
                //colocar algun mensaje
                Toast.makeText(context,"Hola mundo"+e,Toast.LENGTH_SHORT).show();
            }

        }

        public void asignarDatos(int i){
            asignaturaNombre.setText(SesionActual.usuarioActual.getAsignaturas().get(i).getNombre());
            NumeroTxt.setText(i+"");
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
    }
}
