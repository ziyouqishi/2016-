package widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 张佳亮 on 2016/1/31.
 */
public class ClockView extends View {
    private Paint paintCicle;
    private Paint paintDegree;
    private Paint paintHour;
    private Paint paintMinute;
    private int mWidth,mHeight;
    public ClockView(Context context) {
        super(context);
        initView();
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paintCicle=new Paint();
        paintCicle.setStyle(Paint.Style.STROKE);
        paintCicle.setAntiAlias(true);
        paintCicle.setStrokeWidth(5);
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2, paintCicle);

        paintDegree =new Paint();
        paintDegree.setStrokeWidth(3);
        for(int i=0;i<24;i++){
            if(i==0||i==6||i==12||i==18){
                paintDegree.setStrokeWidth(5);
                paintDegree.setTextSize(30);
                canvas.drawLine(mWidth/2,mHeight/2-mWidth/2,mWidth/2,mHeight/2-mWidth/2+60,paintDegree);
                String degree=String.valueOf(i);
                canvas.drawText(degree,mWidth/2-paintDegree.measureText(degree)/2,
                        mHeight/2-mWidth/2+90,paintDegree);

            }else{
                paintDegree.setStrokeWidth(3);
                paintDegree.setTextSize(15);
                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2, mWidth / 2, mHeight / 2 - mWidth / 2 + 30, paintDegree);
                String degree=String.valueOf(i);
                canvas.drawText(degree, mWidth / 2 - paintDegree.measureText(degree) / 2,
                        mHeight / 2 - mWidth / 2 + 60, paintDegree);

            }
            canvas.rotate(15,mWidth/2,mHeight/2);
        }

        paintHour=new Paint();
        paintHour.setStrokeWidth(15);
        paintMinute=new Paint();
        paintMinute.setStrokeWidth(7);
         canvas.save();
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawLine(0,0,100,80,paintHour);
        canvas.drawLine(0,0,100,120,paintMinute);
        canvas.restore();

    }

    void initView(){
        mHeight=500;
        mWidth=400;
    }
}
