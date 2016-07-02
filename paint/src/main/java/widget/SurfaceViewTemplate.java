package widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by 张佳亮 on 2016/2/2.
 */
public class SurfaceViewTemplate extends SurfaceView implements SurfaceHolder.Callback,Runnable {
    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private boolean mIsDrawing;
    private Path mPath;
    private Paint mPaint;
    private int x,y;

    public SurfaceViewTemplate(Context context) {
        super(context);
        initView();
    }

    public SurfaceViewTemplate(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SurfaceViewTemplate(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mIsDrawing=true;
        new Thread(this).start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        mIsDrawing=false;

    }

    void initView(){
        mHolder=getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
        mPath=new Path();
       mPaint=new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
       mPaint.setStrokeWidth(5);
    }

    @Override
    public void run() {
        while(mIsDrawing){
            draw();
            x+=1;
            y=(int)(100*Math.sin(x*2*Math.PI/180)+400);
            mPath.lineTo(x,y);
        }

    }

    void draw(){
        try{
            mCanvas=mHolder.lockCanvas();
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawPath(mPath, mPaint);
           // mCanvas.translate();
        }
        catch (Exception e){

        }
        finally {
            if(mCanvas!=null){
                mHolder.unlockCanvasAndPost(mCanvas);
            }

        }
    }
}
