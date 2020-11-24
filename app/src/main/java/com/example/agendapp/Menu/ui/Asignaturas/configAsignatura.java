package com.example.agendapp.Menu.ui.Asignaturas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.agendapp.Clases.Asignatura;
import com.example.agendapp.Login.SesionActual;
import com.example.agendapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class configAsignatura extends AppCompatActivity {


    TextView nombreAsignaturaTxt;
    TextView numSubTxt;

    EditText nombreTxt;
    EditText creditosTxt;

    Button validarBt;

    ImageButton returnBt;
    //necesitamos la posición de la Asignatura dentro del
    public static int posicion=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion_asignatura);
        Toast.makeText(getApplicationContext(),posicion+"",Toast.LENGTH_SHORT).show();
        iniciar();
    }

    /**
     * Inicializacion de componentes graficos
     * inicar(): se encarga de conectar los objetos java con sus correspondientes en xml
     * Listeners(): le define los onclick a los botones
     * llenarTxt(): Carga los elementos correspondientes a cada Txt desde el usuario y la asignatura
     * focusChange():  Valida si existe algun cambio en los valores de los txt y activa el boton de validar
     */
    public void iniciar(){
        nombreAsignaturaTxt=findViewById(R.id.nombreAsginaturaTxt);
        numSubTxt=findViewById(R.id.numSubTxt);


        nombreTxt=findViewById(R.id.nombreTxt);
        creditosTxt=findViewById(R.id.CreditosTxt);

        validarBt=findViewById(R.id.validarBt);

        returnBt=findViewById(R.id.returnBt);

        llenarTxt();
        Listeners();
        focusChange();

    }
    public void Listeners(){
        validarBt.setClickable(false);
        validarBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=SesionActual.asignatura.getId();
                String nombre=nombreTxt.getText().toString();
                int creditos=Integer.parseInt(creditosTxt.getText().toString());
                modifcarAsignatura(id,nombre,creditos);
            }
        });

        returnBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configAsignatura.this.finish();
            }
        });
    }

    public void llenarTxt(){
        // no es necesidad comprobar si son valores nulos, no deberian llegar nunca valores nulos

        nombreTxt.setText(SesionActual.asignatura.getNombre());
        creditosTxt.setText(SesionActual.asignatura.getCreditos()+"");
        numSubTxt.setText(SesionActual.asignatura.getSubtemas().size()+"");
    }

    public void focusChange(){
        nombreTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    if(!nombreTxt.getText().toString().equals(SesionActual.asignatura.getNombre())
                            && (nombreTxt.getText().toString().length() != 0))
                        validarBt.setClickable(true);
            }
        });

        creditosTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    int creditos=Integer.parseInt(creditosTxt.getText().toString());
                    if(!hasFocus)
                        if(creditos!=SesionActual.asignatura.getCreditos()&&creditos>0)
                            validarBt.setClickable(true);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Error con los datos, reintentelo",Toast.LENGTH_SHORT).show();
                    configAsignatura.this.finish();
                }


            }
        });
    }


    public void modifcarAsignatura(int id, String nombre,  int creditos){
        String url="http://agendapp.atwebpages.com/Asignaturas/modificarAsignatura.php?idA="+id+"&nombre="+nombre+"&creditos="+creditos;
        url=url.replace(" ","%20");
        RequestQueue rq;
        JsonRequest jrq;




        final int cred=creditos;
        final String nom=nombre;
        jrq=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Asignatura asignatura;
                boolean v;

                try {
                    JSONArray json = response.optJSONArray("modificado");
                    v = json.getBoolean(0);
                    asignatura=new Asignatura(nom, cred,SesionActual.asignatura.getTiempoEstudio(),SesionActual.asignatura.getImagen());

                    if(v) {

                        SesionActual.usuarioActual.getAsignaturas().set(posicion, asignatura);
                        Toast.makeText(getApplicationContext(),"Salga y entre de nuevo al apartado de Asignaturas para observar los cambios",Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(getApplicationContext(),"Vuelva a intentarlo",Toast.LENGTH_SHORT).show();
                    }

                    configAsignatura.this.finish();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Error cambiando los datos a la asignatura"+error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        rq = Volley.newRequestQueue (getApplicationContext());
        rq.add(jrq);
    }

}