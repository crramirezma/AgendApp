package com.example.agendapp.Menu.ui.Asignaturas.subCarpetas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agendapp.Adapters.SubTemasAdapter;
import com.example.agendapp.Login.SesionActual;
import com.example.agendapp.Menu.ui.Asignaturas.AsignaturaDialog;
import com.example.agendapp.R;


public class SubtemaActivity extends AppCompatActivity {
    TextView asignatura;
    ImageButton returnBt;
    ImageButton nuevoSubBt;

    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtema);



        iniciar();

    }

    public void iniciar(){
        asignatura=findViewById(R.id.asignaturaTxt);
        asignatura.setText(SesionActual.asignatura.getNombre());

        returnBt=findViewById(R.id.returnBt);
        nuevoSubBt=findViewById(R.id.nuevoSubtemaBt);


        //corregir lo de la posición
        SubTemasAdapter subTemasAdapter=new SubTemasAdapter(getApplicationContext(),this);
        subTemasAdapter.manager=getSupportFragmentManager();
        Toast.makeText(getApplicationContext(),SesionActual.asignatura.getSubtemas().size()+"",Toast.LENGTH_SHORT).show();

        recycler=findViewById(R.id.subtemaRecycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(subTemasAdapter);
        recycler.setHasFixedSize(true);

        listeners();
    }
    public void listeners(){
        returnBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubtemaActivity.this.finish();
            }
        });

        nuevoSubBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SesionActual.asignatura.getSubtemas().size()==5){
                    Toast.makeText(getApplicationContext(),"No se puede crear el subtema, ya se supero el limite de subtemas",Toast.LENGTH_LONG).show();
                }else{
                    crearSubtema();
                }
            }
        });
    }

    /**
     * crearSubtema
     * La funcion se encarga de realizar la consulta en la base de datos con respecto a la creación de un nuevo subtema, llama a las funciones....
     *
     */
    public void crearSubtema(){
        SubtemaDialog subtemaDialog=new SubtemaDialog(this);
        subtemaDialog.show(getSupportFragmentManager(),"Nuevo Dialogo");
    }

}