package com.zhimei.animation;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.MessageQueue;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;


public class FrameAnimationtivity extends Activity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_animationtivity);
        initView();
    }

    void initView(){
        imageView=(ImageView)findViewById(R.id.image);
        AnimationDrawable drawable=(AnimationDrawable)imageView.getDrawable();
        drawable.start();

    }

}
