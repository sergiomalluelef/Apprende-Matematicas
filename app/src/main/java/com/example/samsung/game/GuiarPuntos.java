package com.example.samsung.game;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Samsung on 04/09/2017.
 */

public class GuiarPuntos extends View {

    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path path;
    private Paint dibujar;
    private static final int tolerancia = 60;
    private static final int color_fondo = 0xffffff;
    private List<Point> punto = new ArrayList<Point>();

    private int indiceUltimoPunto = 0;
    private int touchTolerancia;
    private boolean inicioRuta = false;
    Context context;

    public GuiarPuntos(Context context) {
        super(context);
        this.context = context;
        mCanvas = new Canvas();
        path = new Path();
        dibujar = new Paint();
        dibujar.setAntiAlias(true);
        dibujar.setDither(true);
        dibujar.setColor(Color.BLACK);
        dibujar.setStyle(Paint.Style.STROKE);
        dibujar.setStrokeJoin(Paint.Join.ROUND);
        dibujar.setStrokeCap(Paint.Cap.ROUND);
        dibujar.setStrokeWidth(75);
        touchTolerancia = dp2px(tolerancia);

        //Posiciones
        Point p1 = new Point(150, 140);
        Point p2 = new Point(380, 50);
        Point p3 = new Point(380, 600);


        punto.add(p1);
        punto.add(p2);
        punto.add(p3);
    }

    public GuiarPuntos(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCanvas = new Canvas();
        path = new Path();
        dibujar = new Paint();
        dibujar.setAntiAlias(true);
        dibujar.setDither(true);
        dibujar.setColor(Color.BLACK);
        dibujar.setStyle(Paint.Style.STROKE);
        dibujar.setStrokeJoin(Paint.Join.ROUND);
        dibujar.setStrokeCap(Paint.Cap.ROUND);
        dibujar.setStrokeWidth(75);
        touchTolerancia = dp2px(tolerancia);

        //Posiciones
        Point p1 = new Point(150, 140);
        Point p2 = new Point(380, 50);
        Point p3 = new Point(380, 600);


        punto.add(p1);
        punto.add(p2);
        punto.add(p3);

    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        clear();

    }

    //Dibujar
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(color_fondo);
        canvas.drawBitmap(mBitmap, 0, 0, null);
        canvas.drawPath(path, dibujar);

        for (Point point : punto) {
            canvas.drawPoint(point.x, point.y, dibujar);
        }
    }

    private void touch_start(float x, float y) {

        if (verificacion(x, y, indiceUltimoPunto)) {
            path.reset();

            inicioRuta = true;
        } else {

            inicioRuta = false;
        }

    }

    //Dibujar con el dedo
    private void touch_move(float x, float y) {

        if (inicioRuta) {
            path.reset();
            Point p = punto.get(indiceUltimoPunto);
            path.moveTo(p.x, p.y);
            path.lineTo(x, y);
        }
    }


    private void touch_up(float x, float y) {
        path.reset();
        if (verificacion(x, y, indiceUltimoPunto + 1) && inicioRuta) {
            // movimiento terminado en el punto válido para dibujar toda la línea

            // Inicia punto
            Point p = punto.get(indiceUltimoPunto);
            path.moveTo(p.x, p.y);
            // Termina punto
            p = punto.get(indiceUltimoPunto + 1);
            path.lineTo(p.x, p.y);
            mCanvas.drawPath(path, dibujar);
            path.reset();
            // Incremento
            ++indiceUltimoPunto;
            inicioRuta = false;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:

                touch_up(x, y);
                invalidate();

                if(y > 600){
                    Singleton.getInstancia(context).etapas = 1;
                    Log.d("puntos","x:"+x +":y:"+Singleton.getInstancia(context).etapas);
                }

                break;
        }
        return true;
    }

    /**
     * Sets paint
     *
     * @param paint
     */
    public void setPaint(Paint paint) {
        this.dibujar = paint;
    }

    /**
     * Returns image as bitmap
     *
     * @return
     */
    public Bitmap getBitmap() {
        return mBitmap;
    }

    /**
     * Clears canvas
     */
    public void clear() {
        mBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        mBitmap.eraseColor(color_fondo);
        mCanvas.setBitmap(mBitmap);
        invalidate();
    }

    /**
     * Comprueba si el punto de contacto del usuario tiene alguna tolerancia
     */
    private boolean verificacion(float x, float y, int pointIndex) {
        if (pointIndex == punto.size()) {
            // fuera de los límites
            return false;
        }
        Point point = punto.get(pointIndex);
        //punto de cambio.y punto.x en la primera instrucción if
        if (x > (point.x - touchTolerancia) && x < (point.x + touchTolerancia)) {
            if (y > (point.y - touchTolerancia) && y < (point.y + touchTolerancia)) {
                return true;
            }
        }
        return false;
    }

    public List<Point> getPoints() {
        return punto;
    }

    public void setPoints(List<Point> points) {
        this.punto = points;
    }

    private int dp2px(int dp) {
        Resources r = getContext().getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
        return (int) px;
    }
}

