package com.zhimei.list;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Interpolator;
import android.os.Bundle;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {
    private ListView listView;
    private List<String> mDatas;


    private RelativeLayout relativeLayout;
    private int mTouchSlop;//每次滑动的最小距离
    private float firstY,currentY;
    private Direction fangxiang;
    private boolean isDisplay=true;
    private Animator mAnimator,mAnimatorEnd;
    private enum Direction{
        UP,DOWN
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDatas();
        slide2();
    }

    void initView(){
        relativeLayout=(RelativeLayout)findViewById(R.id.reg_one);
        listView=(ListView)findViewById(R.id.list);
        View header =new View(this);
        header.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,
                (int)getResources().getDimension(R.dimen.abc_action_bar_default_height_material)));
        listView.addHeaderView(header);

    }

    void initDatas(){
        mDatas=new ArrayList<String>();
        for(int i='A';i<'Z';i++){
            mDatas.add((char)i+"");
        }
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDatas));
        mTouchSlop= ViewConfiguration.get(this).getScaledTouchSlop();
    }

    private void toolbarAnim(int flag){
        if(mAnimator!=null&&mAnimator.isRunning()){
            mAnimator.cancel();
        }
        if(flag==0){
            mAnimatorEnd=ObjectAnimator.ofFloat(relativeLayout,"translationY",relativeLayout.getTranslationY(),0);
            mAnimatorEnd.start();

        }else{
            mAnimator=ObjectAnimator.ofFloat(relativeLayout,"translationY",relativeLayout.getTranslationY(),-relativeLayout.getHeight());
            mAnimator.start();
        }

    }


    void slide2(){
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        firstY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        currentY = event.getY();
                        if (currentY - firstY > mTouchSlop) {
                            fangxiang = Direction.DOWN;

                        } else if (firstY - currentY > mTouchSlop) {
                            fangxiang = Direction.UP;

                        }

                        if(fangxiang==Direction.UP){
                            if(isDisplay){
                                toolbarAnim(1);
                                isDisplay=!isDisplay;
                            }
                        }else if(fangxiang==Direction.DOWN){
                            if(!isDisplay){
                                toolbarAnim(0);
                                isDisplay=!isDisplay;
                            }
                        }
                        break;

                }

                return false;
            }
        });

    }

}
