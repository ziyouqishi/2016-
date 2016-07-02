package ZdyView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by 张佳亮 on 2016/1/26.
 */
public class Arcwidget extends View {
    private int length=400;
    private int mCircleXY;
    private float mRadius;
    private Paint mCiclePaint;
    private Paint mArcPaint;
    private Paint mTextPaint;
    private int sweepAgle=15;
    public Arcwidget(Context context) {
        super(context);
        initDatas();
    }

    public Arcwidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDatas();
    }

    public Arcwidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDatas();
    }

    void initDatas(){
        mCircleXY=length/2;
        mRadius=(float)(length*0.5/2);
        mCiclePaint=new Paint();
        mCiclePaint.setColor(Color.BLUE);
        mArcPaint=new Paint();
        mArcPaint.setColor(Color.RED);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeWidth(35);
        mTextPaint=new Paint();
        mTextPaint.setColor(Color.GREEN);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=15;i<180;i=i+15){
                    setSweepAgle(i);
                    try{
                        Thread.sleep(90);
                        Log.i("liang", sweepAgle + "");
                    }
                    catch (Exception e){

                    }
                }
            }
        }).start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF mArcRect=new RectF((float)(length*0.1),(float)(length*0.1),
                (float)(length*0.9),(float)(length*0.9));
        canvas.drawCircle(mCircleXY, mCircleXY, mRadius, mCiclePaint);
        canvas.drawArc(mArcRect, 270, sweepAgle, false, mArcPaint);
        canvas.drawText("你好啊", 0, "你好啊".length(), (float) mCircleXY, (float) (mCircleXY + 0.5), mTextPaint);
       // test(canvas,mArcPaint);
    }

   /* void test(final Canvas canvas,final Paint paint){
        final RectF mArcRect=new RectF((float)(length*0.1),(float)(length*0.1),
                (float)(length*0.9),(float)(length*0.9));
        canvas.drawArc(mArcRect, 270, getSweepAgle(), false, p);
    }*/



    public int getSweepAgle() {
        return sweepAgle;
    }

    public void setSweepAgle(int sweepAgle) {
        this.sweepAgle = sweepAgle;
    }
}
