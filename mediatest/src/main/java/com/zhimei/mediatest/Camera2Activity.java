package com.zhimei.mediatest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;


public class Camera2Activity extends Activity {
    private ImageView imageView;
    private  Uri imageUri;
    private static final int TAKE_PHOTO=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera2);
        initView();
    }

    void initView(){
        imageView=(ImageView)findViewById(R.id.images);

    }

    public void takePhotos(View view){
        File output = new File(Environment
                .getExternalStorageDirectory(), System.currentTimeMillis()+".jpg");
        imageUri = Uri.fromFile(output);
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, TAKE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(this,"哈哈哈哈",Toast.LENGTH_SHORT).show();

        if(requestCode==TAKE_PHOTO){
            if(data!=null){
                if(data.hasExtra("data")){
                    //得到的是缩略图
                    Bitmap bitmap=data.getParcelableExtra("data");
                    imageView.setImageBitmap(bitmap);
                }
            }else{
                int width=imageView.getWidth();
                int height=imageView.getHeight();

                BitmapFactory.Options factoryOptions=new BitmapFactory.Options();
                factoryOptions.inJustDecodeBounds=true;
                BitmapFactory.decodeFile(imageUri.getPath(),factoryOptions);

                int imagewidth=factoryOptions.outWidth;
                int imagehight=factoryOptions.outHeight;

                int scaleFactor=Math.max(imagewidth/width,imagehight/height);


                factoryOptions.inJustDecodeBounds=false;
                factoryOptions.inSampleSize=scaleFactor;
                factoryOptions.inPurgeable=true;

                Bitmap bp=BitmapFactory.decodeFile(imageUri.getPath(),factoryOptions);
                imageView.setImageBitmap(bp);


            }
        }
    }
}
