package com.zhimei.materialdesign;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class TestActivity2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_activity2);
    }
    public void haha(View view){
        Toast.makeText(this,  "hahahah", Toast.LENGTH_SHORT).show();

    }

    public void huhu(View view){
        Toast.makeText(this,  "huhuhu", Toast.LENGTH_SHORT).show();

    }


}
