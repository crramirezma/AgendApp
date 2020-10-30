package com.example.agendapp.Menu.ui.Asignaturas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agendapp.R;


public class AsignaturaFragment extends Fragment {

    private AsignaturaViewModel asignaturaViewModel;

    private RecyclerView recycler;


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



        recycler=root.findViewById(R.id.recycler);


        return root;
    }
}