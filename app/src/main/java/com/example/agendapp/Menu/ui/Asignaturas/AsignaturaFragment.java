package com.example.agendapp.Menu.ui.Asignaturas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agendapp.Adapters.AsignaturasAdapter;
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

        //probando el cambio de imagenes

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
        AsignaturasAdapter adapter=new AsignaturasAdapter(getActivity());
        adapter.asignatura=getParentFragment();
        adapter.manager=getParentFragmentManager();


        recycler.setAdapter(adapter);
        
    }

    public void abrirDialogo(){
        AsignaturaDialog asignaturaDialog=new AsignaturaDialog(getActivity());
        asignaturaDialog.show(getParentFragmentManager(),"Nuevo Dialogo");
    }

    public void abrirSubtema(){

    }
}