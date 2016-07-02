package com.zhimei.newui;

import android.app.Activity;
import android.graphics.Outline;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;


public class ViewShaderActivity extends Activity {
    private ImageView imageView1,imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_shader);
        initView();
    }
    void initView(){
        imageView1=(ImageView)findViewById(R.id.image1);
        imageView2=(ImageView)findViewById(R.id.image2);

      /*  ViewOutlineProvider viewOutlineProvider1=new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0,0,view.getWidth(),view.getHeight(),30);

            }
        };


        ViewOutlineProvider viewOutlineProvider2=new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setOval(0,0,view.getWidth(),view.getHeight());

            }
        };

       *//* imageView1.setOutlineProvider(viewOutlineProvider1);
        imageView2.setOutlineProvider(viewOutlineProvider2);*/


    }


}
