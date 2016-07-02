package com.zhimei.paint;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;


public class BitmapDrawableActivity extends Activity {
    private Bitmap bitmap ;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);
        ininView();
    }
    void ininView(){
        imageView=(ImageView)findViewById(R.id.image);


    }

}
