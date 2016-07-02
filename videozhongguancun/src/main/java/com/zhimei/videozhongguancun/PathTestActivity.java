package com.zhimei.videozhongguancun;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;


public class PathTestActivity extends Activity {
    private ImageView iv_after,iv_before;
    private Canvas canvas;
    private Paint paint;
    private Bitmap alterBitmap;
    private  Bitmap brfore;
    private Bitmap after;
    private Path path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_test);
        initView();
    }

    void initView(){
        after= BitmapFactory.decodeResource(getResources(), R.drawable.dipian);
        brfore= BitmapFactory.decodeResource(getResources(),R.drawable.gaobaohe);
        alterBitmap=Bitmap.createBitmap(brfore.getWidth(), brfore.getHeight(), brfore.getConfig());
        path=new Path();

        canvas=new Canvas(alterBitmap);
        paint=new Paint();
        paint.setStrokeWidth(5);
        paint.setColor(Color.GREEN);
        canvas.drawBitmap(brfore, new Matrix(), paint);


        iv_after=(ImageView)findViewById(R.id.iv_after);
        iv_before=(ImageView)findViewById(R.id.iv_pre);
        iv_after.setImageBitmap(after);
        iv_before.setImageBitmap(alterBitmap);


        iv_before.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int x,y;
                Paint paint2=new Paint();
                paint2.setColor(Color.TRANSPARENT);

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        path.reset();
                        path.moveTo(motionEvent.getX(),motionEvent.getY());

                        break;
                    case MotionEvent.ACTION_MOVE:
                        int newX = (int)motionEvent.getX();
                        int newY = (int)motionEvent.getX();
                        path.lineTo(motionEvent.getX(),motionEvent.getX());

                        canvas.drawPath(path,paint);

                        //iv_before.setImageBitmap(alterBitmap);

                        break;
                    default:
                        break;
                }


                return true;
            }
        });
    }

}
