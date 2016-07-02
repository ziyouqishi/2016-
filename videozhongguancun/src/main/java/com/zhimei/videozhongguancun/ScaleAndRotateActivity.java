package com.zhimei.videozhongguancun;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;


public class ScaleAndRotateActivity extends Activity {
    private ImageView imageView1,imageView2;
    private Bitmap oriBitmap,alterBitmap;
    private Canvas canvas;
    private Paint paint;

    private Matrix matrix;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_and_rotate);
        initView();
    }

    void initView(){
        imageView1=(ImageView)findViewById(R.id.iv_after);
        imageView2=(ImageView)findViewById(R.id.iv_pre);
        oriBitmap= BitmapFactory.decodeResource(getResources(),R.drawable.gaobaohe);
        imageView1.setImageBitmap(oriBitmap);
        alterBitmap=Bitmap.createBitmap(oriBitmap.getWidth(),oriBitmap.getHeight(),oriBitmap.getConfig());
        canvas=new Canvas(alterBitmap);
        paint=new Paint();
        paint.setColor(Color.GREEN);
        matrix=new Matrix();


        /**
         * 直接通过矩阵进行赋值
         */
       /* matrix.setValues(new float[]{
                0.5f,0,1,
                0,1,0,
                0,0,1
        });*/


        /*matrix.setScale(0.5f,1.3f);*/

        /**
         * 以其图片终点为圆心，旋转180度
         */

        matrix.setRotate(180,oriBitmap.getWidth()/2,oriBitmap.getHeight()/2);

        canvas.drawBitmap(oriBitmap,matrix,paint);
        imageView2.setImageBitmap(alterBitmap);




    }

}
