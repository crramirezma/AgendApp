package com.example.agendapp.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.agendapp.Login.Login;
import com.example.agendapp.Login.SesionActual;
import com.example.agendapp.R;
import com.example.agendapp.UserActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MenuActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    private ImageButton userBt;
    private TextView nombreNavTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(SesionActual.usuarioActual==null){
            Intent intent=new Intent(this, Login.class);
            this.startActivity(intent);
            this.finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_asignatura, R.id.nav_calculator,R.id.nav_tareas)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setItemIconTintList(null);


        View headerview = navigationView.getHeaderView(0);
        iniciar(headerview);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    private void iniciar(View header){
        userBt=header.findViewById(R.id.userBt);
        nombreNavTxt=header.findViewById(R.id.nombreNavTxt);

        userBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MenuActivity.this , UserActivity.class);
                MenuActivity.this.startActivity(intent);

            }
        });
        nombreNavTxt.setText(SesionActual.usuarioActual.getUsuario());


    }









}