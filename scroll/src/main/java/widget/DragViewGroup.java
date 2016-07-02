package widget;

import android.app.Application;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.Scroller;

import com.zhimei.scroll.R;

/**
 * Created by 张佳亮 on 2016/1/30.
 */
public class DragViewGroup extends FrameLayout {
    private ViewDragHelper mViewDragHelper;
    private View mMenuView,mMainView;
    private int mWidth;
    float startX=0;
    float currentX=0;
    boolean flag=true;
    private Context context;

    public DragViewGroup(Context context) {
        super(context);
        this.context=context;
        initView();
    }

    public DragViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        initView();
    }

    public DragViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        initView();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mMenuView=getChildAt(0);
        mMainView=getChildAt(1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=mMenuView.getMeasuredWidth();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);

    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
       /* switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX=event.getX();
                Log.i("liang",startX+"是startX");
                break;
            case MotionEvent.ACTION_UP:
                currentX=event.getX();
                Log.i("liang",currentX+"currentX");
                if(currentX-startX>0){
                    flag=true;
                    Log.i("liang","向右滑动");

                }else if(currentX-startX<0) {
                    flag = false;
                    Log.i("liang","向左滑动");
                }

        }*/
        return true;
    }

    private void initView(){
        mViewDragHelper=ViewDragHelper.create(this,callback);
    }

    private ViewDragHelper.Callback callback=new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return mMainView==child;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            /**
             * dx描述的是速度
             */
            Log.i("liang","left is"+left);
            Log.i("liang","dx is"+dx);
            if(left<0){
                left=0;//使其不能向左滑动
            }
            return left;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return 0;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            if(mMainView.getLeft()<300){
                mViewDragHelper.smoothSlideViewTo(mMainView,0,0);
                ViewCompat.postInvalidateOnAnimation(DragViewGroup.this);
            }else{
                mViewDragHelper.smoothSlideViewTo(mMainView, 500, 0);
                ViewCompat.postInvalidateOnAnimation(DragViewGroup.this);
                Animation animation= AnimationUtils.loadAnimation(context, R.anim.anim_item);
                mMenuView.setAnimation(animation);

            }
        }

        @Override
        public void onViewDragStateChanged(int state) {
            super.onViewDragStateChanged(state);
           /* if(ViewDragHelper.EDGE_LEFT==1){
                Log.i("liang", "onViewDragStateChanged");
                scroller.startScroll(mMenuView.getScrollX(),
                        mMenuView.getScrollY(),
                        -mMenuView.getScrollX(),
                        -mMenuView.getScrollY());
            }*/

        }
    };

    @Override
    public void computeScroll() {
      if(mViewDragHelper.continueSettling(true)){
          ViewCompat.postInvalidateOnAnimation(this);
      }

       /* if(scroller.computeScrollOffset()){
            mMenuView.scrollTo(scroller.getCurrX(),scroller.getCurrY());
            invalidate();
        }*/
    }
}
