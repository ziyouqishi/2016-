package com.gc.materialdesign.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by 张佳亮 on 2016/2/11.
 */
public class ZdyView extends RelativeLayout {
    public ZdyView(Context context) {
        super(context);
    }

    public ZdyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ZdyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // super.onInterceptTouchEvent(ev);
        return true;
    }

}
