package com.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.gc.materialdesign.views.LayoutRipple;
import com.zhimei.materialdesign.ButtonsActivity;
import com.zhimei.materialdesign.R;

import java.util.List;

/**
 * Created by 张佳亮 on 2016/2/8.
 * 好友列表
 */
public class ChatterListAdapter extends BaseAdapter {
    private List<String> data;
    private LayoutInflater inflater;
    private Context context;
    private Bitmap bitmap;

    public ChatterListAdapter(Context context, List<String> data) {
        this.data=data;
        inflater=LayoutInflater.from(context);
        this.context=context;


    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View covertView,  ViewGroup viewGroup) {
        ViewHolder holder=null;
        if(covertView==null){
            holder=new ViewHolder();
            covertView=inflater.inflate(R.layout.list_item,null);
            holder.test=(TextView)covertView.findViewById(R.id.text);
            holder.layoutRipple=(RelativeLayout)covertView.findViewById(R.id.rippletest);
            holder.imageView=(ImageView)covertView.findViewById(R.id.imagetest);

            covertView.setTag(holder);

        }else{
            holder=(ViewHolder)covertView.getTag();

        }
        holder.test.setText(data.get(position).toString());
        holder.layoutRipple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, position+"hahahah", Toast.LENGTH_SHORT).show();
               // view.setVisibility(View.INVISIBLE);
              /*  Intent intent = new Intent(context, ButtonsActivity.class);
                context.startActivity(intent);*/


            }
        });
       /* holder.layoutRipple.setRippleColor(Color.BLUE);
        holder.layoutRipple.setRippleSpeed(30);*/
      //  holder.telePhone.setText(data.get(position).getMobilePhoneNumber());




        return covertView;
    }

    class ViewHolder{
        public TextView test;
        public RelativeLayout layoutRipple;
        public ImageView imageView;

    }

    void call(String telePhone){
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telePhone));
        //startActivity(intent);
        context.startActivity(intent);


    }


}
