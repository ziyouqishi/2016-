package com.zhimei.materialdesign;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonFloatSmall;
import com.gc.materialdesign.views.LayoutRipple;
import com.widget.ChatterListAdapter;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;


public class TestActivity1 extends Activity {
    private ListView listView;
    private List<String> mDatas;
    private LayoutRipple layoutRipple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_activity1);
        initView();
        Toast.makeText(TestActivity1.this, listView.getChildCount()+"", Toast.LENGTH_SHORT).show();

    }

    void initView(){
        listView=(ListView)findViewById(R.id.list);
        mDatas=new ArrayList<String>();
       // layoutRipple=(LayoutRipple)findViewById(R.id.rippletest);
       View view= LayoutInflater.from(this).inflate(R.layout.list_item,null);


        for(int i='A';i<'Z';i++){
            mDatas.add((char)i+"");
        }
        ChatterListAdapter chatterListAdapter=new ChatterListAdapter(this,mDatas);
        listView.setAdapter(chatterListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(TestActivity1.this, ButtonsActivity.class);
                startActivity(intent);
                Toast.makeText(TestActivity1.this, "hahaha", Toast.LENGTH_SHORT).show();

            }
        });

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });*/


    }




}
