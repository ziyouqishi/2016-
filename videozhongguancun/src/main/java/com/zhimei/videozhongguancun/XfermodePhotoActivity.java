package com.zhimei.videozhongguancun;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;


public class XfermodePhotoActivity extends Activity {
    private ImageView imageView;
    private Bitmap oriBitmap,alterBitmap;
    private Canvas canvas;
    private Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xfermode_photo);
        initView();
    }

    void initView(){
        imageView=(ImageView)findViewById(R.id.image);
        oriBitmap= BitmapFactory.decodeResource(getResources(), R.drawable.test);
        alterBitmap=Bitmap.createBitmap(oriBitmap.getWidth(), oriBitmap.getHeight(), oriBitmap.getConfig());
        canvas=new Canvas(alterBitmap);
        paint=new Paint();
        paint.setColor(Color.GREEN);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DARKEN));

        canvas.drawBitmap(oriBitmap, new Matrix(), paint);

        Bitmap secBitmap=BitmapFactory.decodeResource(getResources(),R.drawable.gaobaohe);

        canvas.drawBitmap(secBitmap, new Matrix(), paint);

        imageView.setImageBitmap(alterBitmap);

    }

}
