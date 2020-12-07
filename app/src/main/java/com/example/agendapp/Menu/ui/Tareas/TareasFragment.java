package com.example.agendapp.Menu.ui.Tareas;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.agendapp.Adapters.TareasAdapter;
import com.example.agendapp.Menu.ui.Asignaturas.AsignaturaFragment;
import com.example.agendapp.R;

public class TareasFragment extends Fragment {
    private RecyclerView recycler;
    private ImageButton addBt;

    private TareasViewModel mViewModel;

    public static TareasFragment newInstance() {
        return new TareasFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.tareas_fragment, container, false);


        //para un mayor orden del codigo, la funcion "iniciar" se encarga de hacer las inicializaciones
        //de los componentes graficos de la vista en cuestion
        //necesita la vista dadas las desventajas de los fragmentos
        iniciar(root);
        Listener(root);
        return root;
    }

    public void iniciar(View root){
        recycler=root.findViewById(R.id.recyclerT);

        addBt=root.findViewById(R.id.addBt);


        //Creando el adaptador para el recyclerView
        TareasAdapter adapter=new TareasAdapter(getActivity());
        adapter.TareaFragment=getParentFragment();
        adapter.manager=getParentFragmentManager();

        //pasandole los datos al recycler
        recycler.setLayoutManager(new LinearLayoutManager(TareasFragment.this.getContext()));
        recycler.setAdapter(adapter);

    }
    public void Listener(View root){
        addBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearTarea();
            }
        });
    }

    //Esta función se encarga de llamar al dialogo que se encargara de subir la nueva tarea
    public void crearTarea(){
        TareaDialog tareaDialog=new TareaDialog(getActivity(),recycler.getAdapter());
        tareaDialog.show(getParentFragmentManager(),"Nuevo dialogo");
    }


}