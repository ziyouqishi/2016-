package com.zhimei.paint;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import utitls.ImageHelper;


public class PixelsBitmaoActivity extends Activity {
    private Bitmap bitmap;
    private int height,width;
    private int color;
    private int r,g,b,a;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pixels_bitmao);
        initView();
    }

    void initView(){
        imageView=(ImageView)findViewById(R.id.image);
        bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.xique);
        imageView.setImageBitmap(bitmap);
    }

    public void dipian(View view){
        Bitmap a= ImageHelper.handleImageNegative(bitmap);
        imageView.setImageBitmap(a);

    }

    public void oldPhone(View view){
        Bitmap a= ImageHelper.oldPhone(bitmap);
        imageView.setImageBitmap(a);


    }

    public void fudiao(View view){
        Bitmap a= ImageHelper.fuDiao(bitmap);
        imageView.setImageBitmap(a);


    }

    public void cancel(View view){
        bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.xique);
        imageView.setImageBitmap(bitmap);


    }


}
