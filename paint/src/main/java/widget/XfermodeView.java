package widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.zhimei.paint.R;

/**
 * Created by 张佳亮 on 2016/2/2.
 */
public class XfermodeView extends View {
    private Bitmap mBgBitmap,mFgBitmap;
    private Paint mpaint;
    private Canvas mCanvas;
    private Path mPath;
    public XfermodeView(Context context) {
        super(context);
        init();
    }

    public XfermodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public XfermodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init(){
        mpaint=new Paint();
        mpaint.setAlpha(0);
        mpaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mpaint.setStyle(Paint.Style.STROKE);
        mpaint.setStrokeJoin(Paint.Join.ROUND);
        mpaint.setStrokeWidth(50);
        mpaint.setStrokeCap(Paint.Cap.ROUND);
        mPath=new Path();
        mBgBitmap= BitmapFactory.decodeResource(getResources(), R.drawable.xique );
        mFgBitmap=Bitmap.createBitmap(mBgBitmap.getWidth(), mBgBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        mCanvas=new Canvas(mFgBitmap);
        mCanvas.drawColor(Color.GRAY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(),event.getY());
                break;
        }

        mCanvas.drawPath(mPath, mpaint);
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
       canvas.drawBitmap(mBgBitmap,0,0,null);
        canvas.drawBitmap(mFgBitmap,0,0,null);
    }
}
