package com.zhimei.mediatest;

import android.app.Activity;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.transition.Scene;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Gallery;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class CamaraActivity extends Activity {
    private SurfaceView surfaceView;
    private RelativeLayout relativeLayout;
    private Camera camera;
    private SoundPool soundPool;
    private int soundID;
    private float volume;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_camara);
        sound();
        initView();
    }

    void initView(){
        surfaceView=(SurfaceView)findViewById(R.id.surfacrview);
        relativeLayout=(RelativeLayout)findViewById(R.id.layout);
        surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceView.getHolder().setFixedSize(176, 144);
        surfaceView.getHolder().setKeepScreenOn(true);
        surfaceView.getHolder().addCallback(new MyCallback());



    }

    private final class MyCallback implements SurfaceHolder.Callback{
        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {


            try {
                camera=Camera.open();
                Camera.Parameters parameters=camera.getParameters();
                //parameters.g
                Log.i("liang","haha");
                /***
                 * 设置摄像头的参数
                 */
                parameters.setPreviewSize(480, 800);
                parameters.setPreviewFrameRate(30);
                parameters.setPictureSize(768, 1024);
                parameters.setJpegQuality(100);
                parameters.setSceneMode(Camera.Parameters.SCENE_MODE_BEACH);
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
                camera.setParameters(parameters);
                /**
                 * 设置人脸识别
                 */
                camera.setFaceDetectionListener(new Camera.FaceDetectionListener() {
                    @Override
                    public void onFaceDetection(Camera.Face[] faces, Camera camera) {
                        Toast.makeText(CamaraActivity.this, "已经识别的人脸个数为" + faces.length + faces[0].rect.centerY(), Toast.LENGTH_SHORT).show();

                    }
                });
                camera.setPreviewDisplay(surfaceHolder);
                camera.startPreview();
                camera.startFaceDetection();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            if(camera!=null){
                camera.release();
                camera.stopFaceDetection();
                camera=null;
            }

        }
    }

    public void takePhoto(View v){
        if(camera!=null){
            camera.takePicture(null,null,new MyPictureCallback());
        }
        soundPool.play(soundID,volume,volume,1,0,1);

    }

    public void takeFocus(View v){
        if(camera!=null){
            camera.autoFocus(new Camera.AutoFocusCallback() {
                @Override
                public void onAutoFocus(boolean b, Camera camera) {
                    Toast.makeText(CamaraActivity.this,"已经完成对焦",Toast.LENGTH_SHORT).show();
                }
            });
        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                relativeLayout.setVisibility(ViewGroup.VISIBLE);
        }
        return super.onTouchEvent(event);

    }
    private final class MyPictureCallback implements Camera.PictureCallback{
        @Override
        public void onPictureTaken(byte[] bytes, Camera camera) {
            try {
                /**
                 * 文件存储
                 */
                File jpgFie=new File(Environment
                        .getExternalStorageDirectory(), System.currentTimeMillis()+".jpg");
                FileOutputStream outputStream=new FileOutputStream(jpgFie);
                outputStream.write(bytes);
                outputStream.close();
                camera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    /**
     * 播放拍照声音
     */

    void sound(){

        soundPool=new SoundPool(4, AudioManager.STREAM_MUSIC,0);
        soundID=soundPool.load(this,R.raw.fv,1);
        AudioManager audioManager=(AudioManager)this.getSystemService(this.AUDIO_SERVICE);
        float maxVolume=audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float currentVolime=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        volume=currentVolime/maxVolume;

    }
}
