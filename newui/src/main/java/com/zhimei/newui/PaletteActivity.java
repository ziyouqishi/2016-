package com.zhimei.newui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;


public class PaletteActivity extends Activity {
    private TextView textView;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.beauty);
        textView=(TextView)findViewById(R.id.text);
        linearLayout=(LinearLayout)findViewById(R.id.line1);
        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrant=palette.getLightMutedSwatch();

              //  getActionBar().setBackgroundDrawable(new ColorDrawable(vibrant.getRgb()));
                Window window=getWindow();
               // window.setStatusBarColor(vibrant.getRgb());te
              //  textView.setBackgroundColor(vibrant.getRgb());
                linearLayout.setBackgroundColor(vibrant.getRgb());

            }
        });


    }


}
