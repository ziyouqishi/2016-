package com.zhimei.videozhongguancun;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;


public class PaintActivity extends Activity {
    private Canvas canvas;
    private Paint paint;
    private Bitmap canvasBitmap;
    private ImageView imageView,imageView2;
    private float startX,startY;
    private float height,width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        initView();
    }

    void initView(){
        imageView=(ImageView)findViewById(R.id.image);
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.test);
        height=getWindowManager().getDefaultDisplay().getHeight();
        width=getWindowManager().getDefaultDisplay().getWidth();

        paint=new Paint();
       /* canvas.drawColor(Color.WHITE);*/
        paint.setStrokeWidth(10);
        paint.setColor(Color.GREEN);

        canvasBitmap=Bitmap.createBitmap(720, 1080, Bitmap.Config.ARGB_8888);

        canvas=new Canvas(canvasBitmap);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = motionEvent.getX();
                        startY = motionEvent.getY();

                        break;
                    case MotionEvent.ACTION_MOVE:
                        float newX = motionEvent.getX();
                        float newY = motionEvent.getY();

                        canvas.drawLine(startX, startY, newX, newY, paint);

                        startX = motionEvent.getX();
                        startY = motionEvent.getY();


                        imageView.setImageBitmap(canvasBitmap);


                        break;
                    default:
                        break;
                }


                return true;
            }
        });
    }

    public void saveBitmap(View view){
         try{
             File file=new File(Environment.getExternalStorageDirectory(),"test.jpg");
             FileOutputStream fileOutputStream=new FileOutputStream(file);
             canvasBitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
             fileOutputStream.close();
             Toast.makeText(this,"图片保存成功",Toast.LENGTH_SHORT).show();

         }
         catch (Exception e){
             Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();

         }


    }


}
