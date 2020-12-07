package com.example.agendapp.Tablero;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import android.os.Environment;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.Vector;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyCanvas extends View {
    protected Paint lapiz, canvasLapiz;
    protected Path dibujador;
    protected Bitmap cache, imagen;
    protected Canvas canvasDibujo;
    protected int colorFondo = Color.WHITE;
    protected Vector Fondo;
    protected boolean modoBrocha;
    protected Context context;
    protected boolean todoListo=false;

    public MyCanvas(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);

        lapiz = new Paint();
        dibujador = new Path();
        lapiz.setAntiAlias(true);
        lapiz.setColor(Color.BLUE);
        lapiz.setDither(true);
        //Configurar el bordeado de la punta
        lapiz.setStrokeJoin(Paint.Join.ROUND);

        canvasLapiz  = new Paint(Paint.DITHER_FLAG); //Difuminado
        lapiz.setStyle(Paint.Style.STROKE);
        lapiz.setStrokeWidth(10f);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        cache = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvasDibujo = new Canvas(cache);

        if(Tablero.nombreTablero != null) cargarTablero(Tablero.nombreTablero);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawColor(Color.BLACK);
        if(imagen != null){
            canvas.drawBitmap(imagen,0 , 0, canvasLapiz);
            //System.out.print("Entró");
        }
        canvas.drawBitmap(cache, 0,0, canvasLapiz);

        canvas.drawPath(dibujador,lapiz);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float xPos = event.getX();
        float yPos = event.getY();
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                dibujador.moveTo(xPos,yPos);
                break;
            case MotionEvent.ACTION_MOVE:
                //dibujador.lineTo(xPos, yPos);
                if(modoBrocha){
                    dibujador.addCircle(xPos, yPos, this.lapiz.getStrokeWidth(), Path.Direction.CW);

                }
                else{
                    dibujador.lineTo(xPos, yPos);
                }
                break;
            case MotionEvent.ACTION_UP:

                if(modoBrocha){
                    dibujador.addCircle(xPos, yPos, this.lapiz.getStrokeWidth(), Path.Direction.CW);
                }
                else{
                    dibujador.lineTo(xPos, yPos);
                }
                canvasDibujo.drawPath(dibujador,lapiz);
                dibujador.reset();
                break;
            default:
                return false;

        }

        invalidate();
        return true;
    }


    public void guardarTablero(String nombre) {

        //this.setDrawingCacheEnabled(true);
        File root = this.getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        File tableros = new File(root, "/Tableros/");
        tableros.mkdirs();
        File imagen = new File(tableros, nombre + ".jpg");



            try {
                if (!imagen.exists()) imagen.createNewFile();
                FileOutputStream out = new FileOutputStream(imagen);
                cache.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.flush();
                out.close();
                Toast.makeText(context,"Hecho ´ç`",Toast.LENGTH_SHORT).show();
            } catch (IOException e) {

                e.printStackTrace();
            }

        //this.setDrawingCacheEnabled(false);
    }

    public boolean existeTablero(String nombre){
        File root = this.getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        File tableros = new File(root,  "/Tableros/");
        tableros.mkdirs();
        File imagen = new File(tableros,  nombre + ".jpg");

        if(imagen.exists()){
            return false;
        }
        else{
            return false;
        }

    }

    public void cargarTablero(String nombre){
        File root = this.getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        File archivo = new File(root, "/Tableros/"+nombre+".jpg");
        if(archivo.exists()) {

            BitmapFactory.Options config = new BitmapFactory.Options();
            config.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap imagen = BitmapFactory.decodeFile(archivo.getAbsolutePath());
            ponerImagen(imagen);
        }
    }

    private void ponerImagen(Bitmap imagen) {
        this.imagen = imagen;
        canvasDibujo.drawBitmap(imagen,0 , 0, canvasLapiz);
        invalidate();
    }

    public void borrado(boolean borramos){
        if(borramos){
            lapiz.setColor(Color.BLACK);
            lapiz.setStrokeWidth(50f);
        }
        else{
            lapiz.setStrokeWidth(10f);
        }

    }

    public void setTamaño(float tam){
        lapiz.setStrokeWidth(tam);
    }

    public void setmodoBrocha(boolean bool){
        this.modoBrocha = bool;
    }
}
