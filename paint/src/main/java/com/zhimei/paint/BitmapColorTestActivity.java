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


public class BitmapColorTestActivity extends Activity {
    private ImageView imageView;
    private Bitmap bitmap;
    float[]yuantu={1,0,0,0,0,
                   0,1,0,0,0,
                   0,0,1,0,0,
                   0,0,0,1,0};

    float[]huidu={0.33f,0.59f,0.11f,0,0,
            0.33f,0.59f,0.11f,0,0,
            0.33f,0.59f,0.11f,0,0,
            0,0,0,1,0};

    float[]fanzhuan={-1,0,0,1,1,
            0,-1,0,1,1,
            0,0,-1,1,1,
            0,0,0,1,0};

    float[]huaijiu={0.393f,0.769f,0.189f,0,0,
            0.349f,0.686f,0.168f,0,0,
            0.272f,0.534f,0.131f,0,0,
            0,0,0,1,0};

    float[]quse={1.5f,1.5f,1.5f,0,-1,
            1.5f,1.5f,1.5f,0,-1,
            1.5f,1.5f,1.5f,0,-1,
            0,0,0,1,0};

    float[]gaobaohe={1.438f,-0.122f,-0.016f,0,-0.03f,
            -0.062f,1.378f,-0.016f,0,0.05f,
            -0.062f,-0.122f,1.438f,0,-0.02f,
            0,0,0,1,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_color_test);
        initView();
    }

    void initView(){
        imageView=(ImageView)findViewById(R.id.preView);
        bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.xique);
    }

    public void huidu(View view){
        Bitmap bp=new ImageHelper().setBitmapByColor(huidu,bitmap);
        imageView.setImageBitmap(bp);

    }

    public void fanzhuan(View view){
        Bitmap bp=new ImageHelper().setBitmapByColor(fanzhuan,bitmap);
        imageView.setImageBitmap(bp);

    }

    public void huaijiu(View view){
        Bitmap bp=new ImageHelper().setBitmapByColor(huaijiu,bitmap);
        imageView.setImageBitmap(bp);

    }

    public void quse(View view){
        Bitmap bp=new ImageHelper().setBitmapByColor(quse,bitmap);
        imageView.setImageBitmap(bp);

    }

    public void gaobaohe(View view){
        Bitmap bp=new ImageHelper().setBitmapByColor(gaobaohe,bitmap);
        imageView.setImageBitmap(bp);

    }

}
