package com.gc.materialdesign.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.*;

/**
 * Created by 张佳亮 on 2016/2/13.
 */
public class ZdyScrollView extends android.widget.ScrollView {
    private int mMaxOverDistance=100;
    public ZdyScrollView(Context context) {
        super(context);
    }

    public ZdyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ZdyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, mMaxOverDistance, isTouchEvent);
    }


    void initView(Context context){
        DisplayMetrics displayMetrics=context.getResources().getDisplayMetrics();
        float density=displayMetrics.density;
        mMaxOverDistance=(int)(density*mMaxOverDistance);
    }

}
