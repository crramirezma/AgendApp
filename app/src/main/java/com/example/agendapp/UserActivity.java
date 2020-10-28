package com.example.agendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agendapp.Clases.Usuario;
import com.example.agendapp.Login.SesionActual;
import com.example.agendapp.Menu.MenuActivity;

import org.w3c.dom.Text;

public class UserActivity extends AppCompatActivity {

    TextView nombre;
    TextView apellido;
    TextView ciudad;
    TextView carrera;
    TextView edad;

    Button modBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        iniciar();


    }

    private void iniciar(){
        Usuario usuario=SesionActual.usuarioActual;
        nombre=findViewById(R.id.nombreTxt);
        apellido=findViewById(R.id.apellidoTxt);
        ciudad=findViewById(R.id.ciudadTxt);
        carrera=findViewById(R.id.carreraTxt);
        edad=findViewById(R.id.edadTxt);

        modBt=findViewById(R.id.modBt);

        //asignando los correspondientes textos a cada edittext, primero comprobar que si halla datos en el usuario
        if(usuario.getNombre()==null){
            nombre.setHint("No ha seleccionado nombre");
        }else{
            nombre.setText(usuario.getNombre());
        }

        if(usuario.getApellido()==null){
            apellido.setHint("No ha seleccionado apellido");
        }else{
            apellido.setText(usuario.getApellido());
        }

        if(usuario.getCarrera()==null){
            carrera.setHint("No ha seleccionado carrera");
        }else{
            carrera.setText(usuario.getCarrera());
        }

        if(usuario.getCiudad()==null){
            ciudad.setHint("No ha seleccionado ciudad");
        }else{
            ciudad.setText(usuario.getCiudad());
        }

        if(usuario.getEdad()==0){
            edad.setHint(0);
        }else{
            edad.setText(usuario.getEdad()+"");
        }



        //al principio no habra cambios, por lo que no es necesario oprimir el boton
        modBt.setClickable(false);

        //se calcula si debe o no activarse el boton
        nombre.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if (!nombre.getText().equals(SesionActual.usuarioActual.getNombre())){
                        modBt.setClickable(true);
                    }
                }
            }
        });
    }

    public void returnOnClick(View view) {
        Intent intent=new Intent(this, MenuActivity.class);
        this.startActivity(intent);
    }

    public void modificarListener(View view) {
        Toast.makeText(getApplicationContext(),"Cambio",Toast.LENGTH_SHORT).show();
    }
}