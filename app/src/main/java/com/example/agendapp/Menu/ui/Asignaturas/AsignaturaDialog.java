package com.example.agendapp.Menu.ui.Asignaturas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.agendapp.Clases.Asignatura;
import com.example.agendapp.Login.SesionActual;
import com.example.agendapp.R;

public class AsignaturaDialog extends AppCompatDialogFragment {
    int posicion;
    EditText nombre;
    EditText creditos;

    public AsignaturaDialog(){

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view =inflater.inflate(R.layout.new_asignatura_layoout,null);
        builder.setView(view)
                .setTitle("Nueva asignatura")
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
                        cred=Integer.parseInt(creditos.getText().toString());


                        SesionActual.usuarioActual.addAsignatura(new Asignatura(nom,cred));
                    }
                });


        nombre=view.findViewById(R.id.nuevoNombreAsignaturaTxt);
        creditos=view.findViewById(R.id.CreditosAsignaturaTxt);
        return builder.create();
    }

}
