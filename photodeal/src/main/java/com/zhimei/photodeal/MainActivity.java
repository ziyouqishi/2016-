package com.zhimei.photodeal;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import adapter.GalleryAdapter;
import adapter.ImageHelper;
import adapter.Photo;


public class MainActivity extends Activity {
    private Gallery gallery;
    private ObjectAnimator objectAnimator,objectAnimator2;
    private AnimatorSet animationSet;
    private List<Photo> datas;
    private ImageView picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    void initView(){
        picture=(ImageView)findViewById(R.id.preView);
        gallery=(Gallery)findViewById(R.id.gallery);
     /*  int[]data={R.drawable.beauty,R.drawable.beauty,R.drawable.beauty,R.drawable.beauty,R.drawable.beauty,R.drawable.beauty
       ,R.drawable.beauty,R.drawable.beauty};*/
        datas=new ArrayList<>();
        final Bitmap yuantu=new ImageHelper().readBitmap(this,R.drawable.yuantu);
        Bitmap dipian=new ImageHelper().readBitmap(this,R.drawable.dipian);
        Bitmap fudiao=new ImageHelper().readBitmap(this,R.drawable.fudiao);
        Bitmap gaobaohe=new ImageHelper().readBitmap(this,R.drawable.gaobaohe);
        Bitmap huaijiu=new ImageHelper().readBitmap(this,R.drawable.huaijiu);
        Bitmap huidu=new ImageHelper().readBitmap(this,R.drawable.huidu);
        Bitmap laozhaopian=new ImageHelper().readBitmap(this,R.drawable.laozhaopian);
        Bitmap quse=new ImageHelper().readBitmap(this,R.drawable.quse);
        Bitmap sumiao=new ImageHelper().readBitmap(this,R.drawable.sumiao);

        datas.add(new Photo(yuantu,"原图"));
        datas.add(new Photo(dipian,"底片"));
        datas.add(new Photo(fudiao,"浮雕"));
        datas.add(new Photo(gaobaohe,"高饱和"));
        datas.add(new Photo(huaijiu,"怀旧"));
        datas.add(new Photo(huidu,"灰度"));
        datas.add(new Photo(laozhaopian,"老照片"));
        datas.add(new Photo(quse,"去色"));
        datas.add(new Photo(sumiao,"素描"));

        animationSet=new AnimatorSet();
        GalleryAdapter galleryAdapter=new GalleryAdapter(datas,this);
        gallery.setAdapter(galleryAdapter);

        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                switch (position){
                    case 0:
                        picture.setImageBitmap(yuantu);
                        break;
                    case 1:
                        picture.setImageBitmap(new ImageHelper().handleImageNegative(yuantu));
                        break;
                    case 2:
                        picture.setImageBitmap(new ImageHelper().fuDiao(yuantu));
                        break;
                    case 3:
                        picture.setImageBitmap(new ImageHelper().setBitmapByColor(ImageHelper.gaobaohe, yuantu));
                        break;
                    case 4:
                        picture.setImageBitmap(new ImageHelper().setBitmapByColor(ImageHelper.huaijiu,yuantu));
                        break;
                    case 5:
                        picture.setImageBitmap(new ImageHelper().setBitmapByColor(ImageHelper.huidu,yuantu));
                        break;
                    case 6:
                        picture.setImageBitmap(new ImageHelper().oldPhone(yuantu));
                        break;
                    case 7:
                        picture.setImageBitmap(new ImageHelper().setBitmapByColor(ImageHelper.quse, yuantu));
                        break;
                    case 8:
                        picture.setImageBitmap(new ImageHelper().createPencli(yuantu));
                        break;
                    default:
                        break;
                }

                objectAnimator = ObjectAnimator.ofFloat(view, "scaleX", 1, 1.5f);
                objectAnimator2 = ObjectAnimator.ofFloat(view, "scaleY", 1, 1.5f);
                animationSet.setDuration(300);
                animationSet.playTogether(objectAnimator, objectAnimator2);
                animationSet.start();

            }

        });

}
    }


