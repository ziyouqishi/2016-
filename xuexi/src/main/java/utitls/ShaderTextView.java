package utitls;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by 张佳亮 on 2016/1/13.
 */
public class ShaderTextView extends TextView {
    private int mViewWidth;
    private LinearGradient linearGradient;
    private Matrix matrix;
    private int traslate;
    private Paint mPaint;

    public ShaderTextView(Context context) {
        super(context);
    }

    public ShaderTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ShaderTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(mViewWidth==0){
            mViewWidth=getMeasuredWidth();
            if(mViewWidth>0){
                mPaint=getPaint();
                linearGradient=new LinearGradient(0,0,mViewWidth,0,new int[]{Color.BLUE,
                0xffffffff,Color.BLUE},null,
                        Shader.TileMode.CLAMP);
                mPaint.setShader(linearGradient);
                matrix=new Matrix();

            }
        }
    }
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        if(matrix!=null){
            traslate+=mViewWidth/5;
            if(traslate>2*mViewWidth){
                traslate=-traslate;
            }
            matrix.setTranslate(traslate,0);
            linearGradient.setLocalMatrix(matrix);
            postInvalidateDelayed(50);
        }

    }
}
