package com.zhimei.paint;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;


public class ColorMatrixActivity extends Activity {
    private Bitmap bitmap;
    private ImageView imageView;
    private GridLayout mGroup;
    private int mEtWidth,mEtHeight;
    private EditText[]mEts=new EditText[20];
    private float[]mColorMatrix=new float[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_matrix);
        initView();

    }

    void initView(){
        bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.xique);
        imageView=(ImageView)findViewById(R.id.image);
        mGroup=(GridLayout)findViewById(R.id.group);
        imageView.setImageBitmap(bitmap);

        mGroup.post(new Runnable() {
            @Override
            public void run() {
                mEtWidth = mGroup.getWidth() / 5;
                mEtHeight = mGroup.getHeight() / 4;
                addEts();
                initMatrix();

            }
        });

    }

    void addEts(){
        for(int i=0;i<20;i++){
            EditText editText=new EditText(this);
            mEts[i]=editText;
            mGroup.addView(editText,mEtWidth,mEtHeight);
        }
    }
    void initMatrix(){
        for(int i=0;i<20;i++){
            if(i%6==0){
                mEts[i].setText(String.valueOf(1));
            }else{
                mEts[i].setText(String.valueOf(0));
            }
        }
    }

    void getMatrix(){
        for(int i=0;i<20;i++){
            mColorMatrix[i]=Float.valueOf(mEts[i].getText().toString());

        }
    }

    void setImageMatrix(){
        Bitmap bmp=Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        android.graphics.ColorMatrix colorMatrix=new android.graphics.ColorMatrix();
        colorMatrix.set(mColorMatrix);

        Canvas canvas=new Canvas(bmp);
        Paint paint=new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap,0,0,paint);
        imageView.setImageBitmap(bmp);
    }

    public void btnChange(View view){
        getMatrix();
        setImageMatrix();
    }

    public void btnReset(View view){
        initMatrix();
        getMatrix();
        setImageMatrix();
    }


}
