package com.zhimei.animation;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;


public class valueAnimatorActivity extends Activity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animator);
        initView();
    }

    void initView(){
        textView=(TextView)findViewById(R.id.textview);
        tvTimer(textView);

    }

    void tvTimer(final View view){
        ValueAnimator valueAnimator= ValueAnimator.ofInt(0,100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ((TextView)view).setText(""+(Integer)valueAnimator.getAnimatedValue());
            }
        });
        valueAnimator.setDuration(100000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.start();
    }
}
