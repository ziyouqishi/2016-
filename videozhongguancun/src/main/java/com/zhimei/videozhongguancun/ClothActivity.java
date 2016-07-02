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


public class ClothActivity extends Activity {
    private ImageView iv_after,iv_before;
    private Canvas canvas;
    private Paint paint;
    private Bitmap alterBitmap;
    private  Bitmap brfore;
    private Bitmap after;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloth);
        initView();
    }
    void initView(){
        after= BitmapFactory.decodeResource(getResources(),R.drawable.dipian);
        brfore= BitmapFactory.decodeResource(getResources(),R.drawable.gaobaohe);
        alterBitmap=Bitmap.createBitmap(brfore.getWidth(),brfore.getHeight(),brfore.getConfig() );

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
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        break;
                    case MotionEvent.ACTION_MOVE:
                        int newX = (int)motionEvent.getX();
                        int newY = (int)motionEvent.getY();
                        /**
                         * 判断X坐标是否超出边界
                         */
                        if(newX>=alterBitmap.getWidth()){
                            newX=alterBitmap.getWidth();
                        }

                        if(newY>=alterBitmap.getHeight()){
                            newY=alterBitmap.getHeight();
                        }
                        if(newX<=0){
                            newX=0;
                        }
                        if(newY<=0){
                            newY=0;
                        }

                        /**
                         * 绘制的痕迹为一个矩形像素块
                         */
                        for(int i=-20;i<20;i++){
                        for(int j=-20;j<20;j++){

                            if(Math.sqrt(i*i+j*j)<=20){ //在半径为30的圆形内
                                x=i+newX;
                                y=j+newY;
                                if(x>0&&x<brfore.getWidth()&&y>0&&y<brfore.getHeight()){
                                    /**
                                     * 必须设置画笔为透明
                                     */
                                    alterBitmap.setPixel(i+newX,j+newY,Color.TRANSPARENT);
                                } else if((newX-2)>0&&(newY-2)>0){
                                    alterBitmap.setPixel(newX-1,newY-1,Color.TRANSPARENT);
                                }else {
                                    Log.i("liang","X坐标是"+newX);
                                    Log.i("liang","Y坐标是"+newY);
                                    alterBitmap.setPixel(newX,newY,Color.TRANSPARENT);
                                }

                            }


                        }
                    }


                        iv_before.setImageBitmap(alterBitmap);



                        break;
                    default:
                        break;
                }


                return true;
            }
        });
    }


}
