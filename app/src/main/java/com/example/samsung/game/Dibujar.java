package com.example.samsung.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Samsung on 30/08/2017.
 */

public class Dibujar extends View {

    public int ancho;
    public int alto;
    private Bitmap bitmap;
    private Path path;
    private Paint paint;
    private Canvas canvas;
    private float varX,varY;
    private static final float tolerancia = 5;
    Context context;

    public Dibujar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        path = new Path();

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(25f);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        bitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }
    private void startTouch(float x, float y){
        path.moveTo(x,y);
        varX = x;
        varY = y;
    }
    private void moveTouch(float x, float y){
        float dx = Math.abs(x - varX);
        float dy = Math.abs(y - varY);

        if(dx >= tolerancia || dy >= tolerancia){
            path.quadTo(varX,varY,(x + varX) / 2, (y + varY) / 2);
            varX = x;
            varY = y;
        }
    }
    public void clearCanvas(){
        path.reset();
        invalidate();
    }
    private void uptouch(){
        path.lineTo(varX,varY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(path,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()){
            case  MotionEvent.ACTION_DOWN:
                startTouch(x,y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                moveTouch(x,y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                uptouch();
                invalidate();
                break;
        }

        return (true);
    }
}
