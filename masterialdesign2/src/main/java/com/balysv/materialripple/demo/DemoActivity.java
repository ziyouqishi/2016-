package com.balysv.materialripple.demo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;



public class DemoActivity extends Activity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo);
        View view1=findViewById(R.id.ripple_layout_1);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DemoActivity.this,DemoListActivity.class);
                startActivity(intent);
            }
        });
        View view = findViewById(R.id.ripple_layout_2);
        MaterialRippleLayout.on(view)
            .rippleColor(Color.parseColor("#FF0000"))
            .rippleAlpha(0.2f)
            .rippleHover(true)
            .create();


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DemoActivity.this, DemoRecyclerActivity.class);
                startActivity(intent);
            }
        });


    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_button, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.switch_list) {
            startActivity(new Intent(this, DemoListActivity.class));
            finish();
            return true;
        } else if (id == R.id.switch_recycler) {
            startActivity(new Intent(this, DemoRecyclerActivity.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
