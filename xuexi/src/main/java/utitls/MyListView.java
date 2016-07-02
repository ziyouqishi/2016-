package utitls;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ListView;

/**
 * Created by 张佳亮 on 2016/1/21.
 */
public class MyListView extends ListView {
    private int mMaxOverDistance=100;
    public MyListView(Context context) {
        super(context);
        initView(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, 80, isTouchEvent);
    }

    void initView(Context context){
        DisplayMetrics displayMetrics=context.getResources().getDisplayMetrics();
        float density=displayMetrics.density;
        mMaxOverDistance=(int)(density*mMaxOverDistance);
    }


}
