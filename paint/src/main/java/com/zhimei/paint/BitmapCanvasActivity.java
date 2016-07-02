package com.zhimei.paint;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;


public class BitmapCanvasActivity extends Activity {
    private Canvas canvas;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_canvas);
        initView();
    }
    void initView(){
        imageView=(ImageView)findViewById(R.id.image);
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.xinghai);
        canvas=new Canvas();
        canvas.setBitmap(bitmap);
        Paint circlePaint=new Paint();
        circlePaint.setColor(Color.RED);
        circlePaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0, 0, 20, circlePaint);
        imageView.setImageBitmap(bitmap);
    }



}
