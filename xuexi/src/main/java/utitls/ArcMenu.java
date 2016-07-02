package utitls;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.zhimei.xuexi.R;


/**
 * Created by 张佳亮 on 2016/1/21.
 */
public class ArcMenu extends ViewGroup implements View.OnClickListener {
    private static final int POS_LEFT_TOP=0;
    private static final int POS_LEFT_BOTTOM=1;
    private static final int POS_RIGHT_TOP=2;
    private static final int POS_RIGHT_BOTTOM=3;
    private Position mPosition=Position.RIGHT_BOTTOM;
    private int mRadius;
    private Staus mCurrentStaus=Staus.CLOSE;
    private OnMenuItemClickListener mMenItemClickListener;
    /**
     * 菜单的主按钮
     */
    private View mCButton;

    /**
     * 菜单的位置
     */
    public enum Position{
        LEFT_TOP,LEFT_BOTTOM,RIGHT_TOP,RIGHT_BOTTOM

    }
    /**
     * 菜单的状态
     */
    public enum Staus{
        OPEN,CLOSE
    }

    /**
     * 点击子菜单项的回调接口
     */
    public interface OnMenuItemClickListener{
        void onClick(View view, int pos);
    }
    public ArcMenu(Context context) {
       super(context);
    }

    public ArcMenu(Context context, AttributeSet attrs) {
     super(context,attrs);
        /**
         * 默认半径设置为100dip
         * 将100转化为100dip，如果不这么写，可能是100px。
         */
        mRadius=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,100,
                getResources().getDisplayMetrics());
        /**
         * 获取配置文件里面的各种属性值
         */
        TypedArray a=context.obtainStyledAttributes(attrs, R.styleable.ArcMenu);

        /**
         * 因为在配置文件里面将position定义为枚举类型，每个position对应一个整数值
         * 所以根据获取到的整数值便可以得到position
         */
        int pos=a.getInt(R.styleable.ArcMenu_position,POS_RIGHT_BOTTOM);
        switch (pos){
            case POS_LEFT_TOP:
                mPosition=Position.LEFT_TOP;
                break;
            case POS_LEFT_BOTTOM:
                mPosition=Position.LEFT_BOTTOM;
                break;
            case POS_RIGHT_TOP:
                mPosition=Position.RIGHT_TOP;
                break;
            case POS_RIGHT_BOTTOM:
                mPosition=Position.RIGHT_BOTTOM;
                break;
            default:
                break;
        }

        mRadius=(int)a.getDimension(R.styleable.ArcMenu_radius,TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,100,
                getResources().getDisplayMetrics()));
        Log.i("liang",mRadius+"");

        a.recycle();
    }

    public ArcMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(changed){
            /**
             * 将主Button进行定位
             */
            layoutCButton();

            /**
             * 将子View进行定位
             * cl和ct相当于坐标系中的x轴和y轴
             */

            int count=getChildCount();
            Log.i("liang",count+"");
            for(int i=0;i<count-1;i++){
                View child=getChildAt(i+1);
                child.setVisibility(View.GONE);

                int cl=(int)(mRadius*Math.sin(Math.PI/2/(count-2)*i));
                int ct=(int)(mRadius*Math.cos(Math.PI/2/(count-2)*i));
                int cWidth=child.getMeasuredWidth();
                int cHeight=child.getMeasuredHeight();

                if(mPosition==Position.LEFT_BOTTOM||mPosition==Position.RIGHT_BOTTOM){
                    ct=getMeasuredHeight()-cHeight-ct;
                }
                if(mPosition==Position.RIGHT_TOP||mPosition==Position.RIGHT_BOTTOM){
                    cl=getMeasuredWidth()-cWidth-cl;
                }

                child.layout(cl,ct,cl+cWidth,ct+cHeight);



            }
        }


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        /**
         * 测量所有的childView，因为GroupView的大小取决于childView，因此要遍历所有的childView来确定GroupView的大小。
         */
        int count=getChildCount();
        for(int i=0;i<count;i++){
            measureChild(getChildAt(i),widthMeasureSpec,heightMeasureSpec);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setOnMenItemClickListener(OnMenuItemClickListener mMenItemClickListener) {
        this.mMenItemClickListener = mMenItemClickListener;
    }

    void layoutCButton(){
        mCButton=getChildAt(0);
        mCButton.setOnClickListener(this);

        /**
         * 一个控件的位置由left,top,Bottom,right决定
         * l为left，t为top
         * 实际上，left和top对应坐标系中的x轴和y轴
         *
         */

        int l=0;
        int t=0;

        /**
         * 表示中心Button的的宽和高
         */
        int width=mCButton.getMeasuredWidth();
        int height=mCButton.getMeasuredHeight();

        switch (mPosition){
            case LEFT_TOP:
                l=0;
                t=0;
                break;
            case LEFT_BOTTOM:
                l=0;
                t=getMeasuredHeight()-height;
                break;
            case RIGHT_TOP:
                l=getMeasuredWidth()-width;
                t=0;
                break;
            case RIGHT_BOTTOM:
                l=getMeasuredWidth()-width;
                t=getMeasuredHeight()-height;
                break;
        }
        mCButton.layout(l, t, l + width, t + width);
    }

    @Override
    public void onClick(View view){
        mCButton=findViewById(R.id.id_button);
        /**
         * 点击主菜单的Button，则进行旋转
         */
        rotateCButton(view, 0f, 360f, 400);
        toggleMenu(400);

    }

    private void rotateCButton(View view,float start,float end,int duration){

        RotateAnimation anim=new RotateAnimation(start,end, Animation.RELATIVE_TO_SELF,
                0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        anim.setDuration(duration);
        anim.setFillAfter(true);
        view.startAnimation(anim);

    }

    public void toggleMenu(int duration){
        /**
         * 为MenuItem添加平移动画和旋转动画
         */
        int count=getChildCount();
        for(int i=0;i<count-1;i++){
            final View childView=getChildAt(i+1);
            childView.setVisibility(View.VISIBLE);

            int cl=(int)(mRadius*Math.sin(Math.PI/2/(count-2)*i));
            int ct=(int)(mRadius*Math.cos(Math.PI / 2 / (count - 2) * i));

            int xflag=1;
            int yflag=1;

            if(mPosition==Position.LEFT_TOP||mPosition==Position.LEFT_BOTTOM){
               xflag=-1;
            }
            if(mPosition==Position.RIGHT_TOP||mPosition==Position.LEFT_TOP){
                yflag=-1;
            }

            AnimationSet animSet=new AnimationSet(true);
            Animation tranAnim=null;
            /**
             * 打开
             */
            if(mCurrentStaus==Staus.CLOSE){
                tranAnim=new TranslateAnimation(xflag*cl,0,yflag*ct,0);
                childView.setClickable(true);
                childView.setFocusable(true);


            }else{   //关闭
                tranAnim=new TranslateAnimation(0,xflag*cl,0,yflag*ct);
                childView.setClickable(false);
                childView.setFocusable(false);

            }
            tranAnim.setFillAfter(true);
            tranAnim.setDuration(duration);
            tranAnim.setStartOffset(i*30);

            tranAnim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    if(mCurrentStaus==Staus.CLOSE){
                        childView.setVisibility(View.GONE);
                    }

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            /**
             * 为子Item添加旋转动画
             */
            RotateAnimation anim=new RotateAnimation(0,720, Animation.RELATIVE_TO_SELF,
                    0.5f,Animation.RELATIVE_TO_SELF,0.5f);
            anim.setDuration(duration);
            anim.setFillAfter(true);

            animSet.addAnimation(anim);
            animSet.addAnimation(tranAnim);

            childView.startAnimation(animSet);

            final int pos=i+1;
            childView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mMenItemClickListener!=null)
                        mMenItemClickListener.onClick(childView,pos);
                        menuItemAnim(pos - 1);
                        changeStatus();


                }
            });


        }
        /**
         * 切换菜单状态
         */
        changeStatus();

    }

    void changeStatus(){
        mCurrentStaus=(mCurrentStaus==Staus.CLOSE?Staus.OPEN:Staus.CLOSE);

    }

    /**
     * 添加子菜单的点击动画
     * @param pos
     */
    void menuItemAnim(int pos){
        for(int i=0;i<getChildCount()-1;i++){
            View childView=getChildAt(i+1);
            if(i==pos){
                childView.startAnimation(scaleBigAnim(400));

            }else{
                childView.startAnimation( scaleSmallAnim(400));
            }

            childView.setClickable(false);
            childView.setFocusable(false);
        }

    }

    private Animation scaleSmallAnim(int duration){
        AnimationSet animationSet=new AnimationSet(true);

        ScaleAnimation scaleAnimation=new ScaleAnimation(1.0f,0.0f,1.0f,0.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        AlphaAnimation alphaAnimation=new AlphaAnimation(1f,0.0f);

        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);

        animationSet.setDuration(duration);
        animationSet.setFillAfter(true);
        return animationSet;

    }


    /**
     * 子菜单变大透明度降低的动画
     * @param duration
     * @return
     */
    private Animation scaleBigAnim(int duration){
        AnimationSet animationSet=new AnimationSet(true);

        ScaleAnimation scaleAnimation=new ScaleAnimation(1.0f,4.0f,1.0f,4.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        AlphaAnimation alphaAnimation=new AlphaAnimation(1f,0.0f);

        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);

        animationSet.setDuration(duration);
        animationSet.setFillAfter(true);

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        return animationSet;

    }

    /**
     * 判断菜单是否展开
     * @return
     */
    public boolean isOpen(){
        return mCurrentStaus==Staus.OPEN;
    }
}
