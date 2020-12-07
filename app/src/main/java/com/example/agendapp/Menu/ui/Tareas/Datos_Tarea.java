package com.example.agendapp.Menu.ui.Tareas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.agendapp.Clases.Asignatura;
import com.example.agendapp.Clases.Tarea;
import com.example.agendapp.Login.SesionActual;
import com.example.agendapp.Menu.ui.Asignaturas.configAsignatura;
import com.example.agendapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Datos_Tarea extends AppCompatActivity {
    //componentes graficos de la aplicación
    Button validar;
    ImageButton returnBt;
    EditText nombreTxt;
    Spinner estadoSpin;

    //con este objeto se hara el correspondiente control para subir a la base de datos
    private Tarea t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_tarea);

        iniciar();
        Listeners();
    }

    public void iniciar(){
        validar=findViewById(R.id.validarBt);
        nombreTxt=findViewById(R.id.nombreTxt);
        estadoSpin=findViewById(R.id.estadoSpinner);
        returnBt=findViewById(R.id.returnBt);

        //iniciando los datos que van dentro del spinner
        ArrayList<String> estados=new ArrayList<>();

        //Ya que en la clase "Tarea" se cuenta con los Strings fijos que se...
        //relacionan con los estados, aqui solo se referencian
        estados.add(Tarea.pendiente);
        estados.add(Tarea.proceso);
        estados.add(Tarea.completado);

        ArrayAdapter adapter=new ArrayAdapter(Datos_Tarea.this, android.R.layout.simple_spinner_dropdown_item,estados);
        estadoSpin.setAdapter(adapter);

        asignarDatos();
    }

    public void Listeners(){
        validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t.setnombreTarea(nombreTxt.getText().toString());

                modificarTarea();
            }
        });

        estadoSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String elemento=estadoSpin.getAdapter().getItem(position).toString();

                t.setestadoTarea(Tarea.getNumEstado(elemento));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        returnBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Datos_Tarea.this.finish();
            }
        });

    }
    public void asignarDatos(){
        Tarea tarea=SesionActual.usuarioActual.getTareas().get(SesionActual.posTarea);
        t=tarea;
        nombreTxt.setText(tarea.getnombreTarea());
        estadoSpin.setSelection(tarea.getestadoTarea()-1);
    }

    public void modificarTarea(){
        String url="http://agendapp.atwebpages.com/Tareas/modificarTarea.php?idTarea="+t.getId()+"&nombreTarea="+t.getnombreTarea()+"&estado="+t.getestadoTarea();
        url=url.replace(" ","%20");
        RequestQueue rq;
        JsonRequest jrq;

        jrq=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Asignatura asignatura;
                boolean v;

                try {
                    JSONArray json = response.optJSONArray("modificado");
                    v = json.getBoolean(0);

                    if(v) {

                        SesionActual.usuarioActual.getTareas().set(SesionActual.posTarea,t);
                        Toast.makeText(getApplicationContext(),"Salga y entre de nuevo al apartado de Tareas para observar los cambios",Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(getApplicationContext(),"Vuelva a intentarlo",Toast.LENGTH_SHORT).show();
                    }

                    Datos_Tarea.this.finish();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Error cambiando los datos a la Tarea"+error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        rq = Volley.newRequestQueue (getApplicationContext());
        rq.add(jrq);

    }
}