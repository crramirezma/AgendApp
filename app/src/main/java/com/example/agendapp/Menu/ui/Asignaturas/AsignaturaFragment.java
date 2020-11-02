package com.example.agendapp.Menu.ui.Asignaturas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agendapp.Adapters.AsignaturasAdapter;
import com.example.agendapp.Clases.Asignatura;
import com.example.agendapp.Login.SesionActual;
import com.example.agendapp.R;


public class AsignaturaFragment extends Fragment {

    private AsignaturaViewModel asignaturaViewModel;

    private RecyclerView recycler;
    private ImageButton NuevaAsignatura;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        asignaturaViewModel =
                new ViewModelProvider(this).get(AsignaturaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_asignatura, container, false);
        /*final TextView textView = root.findViewById(R.id.text_gallery);
        asignaturaViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        iniciar(root);
        return root;
    }
    public void iniciar(View root){
        recycler=root.findViewById(R.id.recycler);
        NuevaAsignatura=root.findViewById(R.id.NuevaAsignaturaBt);
        NuevaAsignatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirDialogo();
            }
        });




        recycler.setLayoutManager(new LinearLayoutManager(AsignaturaFragment.this.getContext()));
        AsignaturasAdapter adapter=new AsignaturasAdapter(getContext());
        recycler.setAdapter(adapter);
        
    }

    public void abrirDialogo(){
        AsignaturaDialog asignaturaDialog=new AsignaturaDialog();
        asignaturaDialog.show(getParentFragmentManager(),"Nuevo Dialogo");
    }
}