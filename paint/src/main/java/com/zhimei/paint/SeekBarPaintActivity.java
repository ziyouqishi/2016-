package com.zhimei.paint;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SeekBar;

import utitls.ImageHelper;


public class SeekBarPaintActivity extends Activity {
    private ImageView imageView;
    private SeekBar seekBar1,seekBar2,seekBar3;
    private static final int MID_VALUE=10;
    private float mHue=1,mSaturation=1,mLum=1;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar_paint);
        initView();
    }

    void initView(){
        bitmap= ImageHelper.readBitmap(this,R.drawable.xinghai);
        seekBar1=(SeekBar)findViewById(R.id.sediao);
        seekBar2=(SeekBar)findViewById(R.id.baoheduo);
        seekBar3=(SeekBar)findViewById(R.id.liangdu);
        imageView=(ImageView)findViewById(R.id.image);
        imageView.setImageBitmap(ImageHelper.handleImageEffect(bitmap,mHue,mSaturation,mLum));

        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                mHue=(progress-MID_VALUE)*1.0F/MID_VALUE*180;
                imageView.setImageBitmap(ImageHelper.handleImageEffect(bitmap,mHue,mSaturation,mLum));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                mSaturation=progress*1.0F/MID_VALUE;
                imageView.setImageBitmap(ImageHelper.handleImageEffect(bitmap,mHue,mSaturation,mLum));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                mLum=progress*1.0F/MID_VALUE;
                imageView.setImageBitmap(ImageHelper.handleImageEffect(bitmap,mHue,mSaturation,mLum));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }






}
