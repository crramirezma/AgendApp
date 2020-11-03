package com.example.agendapp.Adapters;

import android.content.Context;
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
import com.example.agendapp.Menu.ui.Asignaturas.AsignaturaDialog;
import com.example.agendapp.Menu.ui.Asignaturas.subCarpetas.SubTemaFragment;
import com.example.agendapp.R;

import java.util.ArrayList;

public class AsignaturasAdapter extends RecyclerView.Adapter<AsignaturasAdapter.asignaturasHolder>{
    public Context context;

    public AsignaturasAdapter(Context context){
        this.context=context;
    }

    @NonNull
    @Override
    public asignaturasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.asignatura_fragment,null,false);
        /*RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);*/
        return new asignaturasHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull asignaturasHolder holder, int position) {
        try{
            holder.context=context;
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

        Context context;

        public asignaturasHolder(@NonNull View itemView) {
            super(itemView);

            asignaturaNombre=itemView.findViewById(R.id.NombreAsignatura);
            btAsignatura=itemView.findViewById(R.id.btAsignatura);
            NumeroTxt=itemView.findViewById(R.id.NumeroTxt);

            btAsignatura.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //abrirDialogo(Integer.parseInt(NumeroTxt.getText().toString()));

                    FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                    Toast.makeText(context,SesionActual.usuarioActual.getAsignaturas().get(Integer.parseInt(NumeroTxt.getText().toString())).getId()+"",Toast.LENGTH_SHORT).show();
                    /*Fragment nuevoFragmento = new SubTemaFragment();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.fragment_as, nuevoFragmento);
                    transaction.addToBackStack(null);
                    transaction.commit();*/
                }
            });
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
