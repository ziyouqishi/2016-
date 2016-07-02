package widgets;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhimei.newui.R;

import java.util.List;

/**
 * Created by 张佳亮 on 2016/2/5.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> mData;

    public RecyclerAdapter(List<String> data) {
        this.mData=data;
    }
    public class ViewHolderaa extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textView;
        public ViewHolderaa(View itemView) {
            super(itemView);
            textView=(TextView)itemView;
            textView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(itemClickListener!=null){
                itemClickListener.onItemClick(view,getPosition());
            }

        }

    }

    @Override
    public ViewHolderaa onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.re_item,viewGroup,false);
        return new ViewHolderaa(v);
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

    }

    public void onBindViewHolder(ViewHolderaa viewHolder, int i) {
        viewHolder.textView.setText(mData.get(i)+i);

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public OnItemClickListener itemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


}
