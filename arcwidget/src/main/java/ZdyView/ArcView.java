package ZdyView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by 张佳亮 on 2016/2/7.
 */
public class ArcView extends SurfaceView implements SurfaceHolder.Callback,Runnable {
    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private boolean mIsDrawing;



    private int length=400;
    private int mCircleXY;
    private float mRadius;
    private Paint mCiclePaint;
    private Paint mArcPaint;
    private Paint mTextPaint;
    private int sweepAgle=15;

    public ArcView(Context context) {
        super(context);
        initView();
    }

    public ArcView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ArcView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mIsDrawing=true;
        new Thread(this).start();
        changeSweepAgle();

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        mIsDrawing=false;

    }

    @Override
    public void run() {
        while (mIsDrawing){
            draw();
        }
    }

    void initView(){
        mHolder=getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);


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
    }

    void draw(){
        try {
            mCanvas=mHolder.lockCanvas();
            mCanvas.drawColor(Color.WHITE);
            RectF mArcRect=new RectF((float)(length*0.1),(float)(length*0.1),
                    (float)(length*0.9),(float)(length*0.9));
            mCanvas.drawCircle(mCircleXY, mCircleXY, mRadius, mCiclePaint);
            mCanvas.drawArc(mArcRect, 270, sweepAgle, false, mArcPaint);
            mCanvas.drawText("你好啊", 0, "你好啊".length(), (float) mCircleXY, (float) (mCircleXY + 0.5), mTextPaint);
        }
        catch (Exception e) {

        }finally {
            if(mCanvas!=null){
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }

    void changeSweepAgle(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=15;i<180;i=i+1){
                    sweepAgle=i;
                    try{
                        Thread.sleep(30);
                        Log.i("liang", sweepAgle + "");
                    }
                    catch (Exception e){

                    }
                }
            }
        }).start();
    }
}
