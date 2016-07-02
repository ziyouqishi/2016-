package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhimei.photodeal.R;

import java.util.List;

/**
 * Created by 张佳亮 on 2016/2/15.
 */
public class GalleryAdapter extends BaseAdapter {
    private int[] data;
    private LayoutInflater inflater;
    private Context context;
    private List<Photo>datas;

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View covertView, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if(covertView==null){
            holder=new ViewHolder();
            covertView=inflater.inflate(R.layout.galleryitem,null);
            holder.imageView=(ImageView)covertView.findViewById(R.id.imagetest);
            holder.textView=(TextView)covertView.findViewById(R.id.shuoming);
            covertView.setTag(holder);

        }else{
            holder=(ViewHolder)covertView.getTag();

        }

        holder.imageView.setImageBitmap(datas.get(position).getBitmap());
        holder.textView.setText(datas.get(position).getDescription());

        return covertView;
    }

    public GalleryAdapter(List<Photo>datas,Context context) {
       /* this.data = data;*/
        this.datas=datas;

        inflater=LayoutInflater.from(context);
        this.context=context;
    }

    class ViewHolder{
        public ImageView imageView;
        public TextView textView;

    }
}
