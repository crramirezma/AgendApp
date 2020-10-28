package com.example.agendapp.tablero;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.agendapp.R;

public class Tablero extends AppCompatActivity {

    MyCanvas mycanvas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mycanvas = new MyCanvas(this, null);
        setContentView(mycanvas);

    }
}