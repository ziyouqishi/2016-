package com.zhimei.animation;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;


public class LayoutAnimationActivity extends Activity {
    private ListView listView;
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_animation);
        initView();
        initDatas();
    }

    void initView(){
        listView=(ListView)findViewById(R.id.list);


    }

    void initDatas(){
        mDatas=new ArrayList<String>();
        for(int i='A';i<'Z';i++){
            mDatas.add((char)i+"");
        }
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDatas));


    }

}
