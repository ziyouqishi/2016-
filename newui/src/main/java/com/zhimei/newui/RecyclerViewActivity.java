package com.zhimei.newui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import widgets.RecyclerAdapter;


public class RecyclerViewActivity extends Activity {
    private RecyclerView recyclerView;
    private ArrayList<String> data;
    private RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        initView();
    }
    void initView(){
        data=new ArrayList<>();
        recyclerView=(RecyclerView)findViewById(R.id.rc_list);
       for(int i='A';i<'Z';i++){
           data.add((char)i+"");

        }

        recyclerAdapter=new RecyclerAdapter(data);
        recyclerView.setAdapter(recyclerAdapter);


    }


}
