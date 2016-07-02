package com.zhimei.xuexi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import utitls.ArcMenu;
import utitls.MyListView;


public class ArcMenuActivity extends Activity {
    private MyListView mlistView;
    private ArcMenu arcMenu;
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arc_menu);
        initView();
        initDatas();
        initevent();
    }
    void initDatas(){
        mDatas=new ArrayList<String>();
        for(int i='A';i<'Z';i++){
            mDatas.add((char)i+"");
        }
        mlistView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDatas));

    }
    void initView(){
        mlistView=(MyListView)findViewById(R.id.id_listview);
        arcMenu=(ArcMenu)findViewById(R.id.id_menu);
        arcMenu.setOnMenItemClickListener(new ArcMenu.OnMenuItemClickListener() {
            @Override
            public void onClick(View view, int pos) {
                Toast.makeText(ArcMenuActivity.this,""+ view.getTag(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * 设置ListView的滑动监听，
     * 滑动时，将菜单设为GONE；
     */
    void initevent(){
        mlistView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                if(arcMenu.isOpen()){
                    arcMenu.toggleMenu(400);
                }

            }
        });
    }



}
