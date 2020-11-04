package com.example.agendapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agendapp.Login.SesionActual;
import com.example.agendapp.R;

public class SubTemasAdapter extends RecyclerView.Adapter<SubTemasAdapter.subHolder>{

    public Context context;
    public int posicion;

    public SubTemasAdapter(Context context, int posicion){
        this.context=context;
        this.posicion=posicion;
    }

    @NonNull
    @Override
    public SubTemasAdapter.subHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.asignatura_fragment,null,false);
        /*RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);*/
        return new SubTemasAdapter.subHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SubTemasAdapter.subHolder holder, int position) {
        try{
            holder.context=context;
            holder.asignarDatos(position,posicion);

        }catch (IndexOutOfBoundsException e){

        }
    }

    @Override
    public int getItemCount() {
        return SesionActual.usuarioActual.getAsignaturas().get(posicion).getsubtemas().size();
    }
    public class subHolder extends RecyclerView.ViewHolder{
        Context context;

        TextView asignaturaNombre;
        ImageButton btAsignatura;
        TextView NumeroTxt;

        public subHolder(@NonNull View itemView) {
            super(itemView);

            asignaturaNombre=itemView.findViewById(R.id.NombreAsignatura);
            btAsignatura=itemView.findViewById(R.id.btAsignatura);
            NumeroTxt=itemView.findViewById(R.id.NumeroTxt);
        }

        public void asignarDatos(int i,int posicion){
            asignaturaNombre.setText(SesionActual.usuarioActual.getAsignaturas().get(posicion).getsubtemas().get(i).getNombreSubtema());
            NumeroTxt.setText(i+"");
        }
    }


}
