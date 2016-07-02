package utitls;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

import java.io.InputStream;

/**
 * Created by 张佳亮 on 2016/1/31.
 */
public class ImageHelper {

    public static Bitmap handleImageEffect(Bitmap bm,float hue,float saturation,float lum){
        Bitmap bmp=Bitmap.createBitmap(bm.getWidth(),bm.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bmp);
        Paint paint=new Paint();

        ColorMatrix hueMatrix=new ColorMatrix();
        hueMatrix.setRotate(0,hue);
        hueMatrix.setRotate(1,hue);
        hueMatrix.setRotate(2, hue);

        ColorMatrix saturationMatrix=new ColorMatrix();
        saturationMatrix.setSaturation(saturation);

        ColorMatrix lumMatriX=new ColorMatrix();
        lumMatriX.setScale(lum,lum,lum,1);

        ColorMatrix imageMatrix=new ColorMatrix();
        imageMatrix.postConcat(hueMatrix);
        imageMatrix.postConcat(saturationMatrix);
        imageMatrix.postConcat(lumMatriX);

        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
        canvas.drawBitmap(bm,0,0,paint);
       return bmp;
    }

    public static Bitmap readBitmap(Context context, int resId) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
        opts.inPurgeable = true;
        opts.inInputShareable = true;
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opts);
    }

    /**
     * 底片效果
     * @param bm
     * @return
     */

    public static Bitmap handleImageNegative(Bitmap bm){
        int width=bm.getWidth();
        int height=bm.getHeight();
        int color;
        int r,g,b,a;
        Bitmap bmp=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        int[]oldPx=new int[width*height];
        int[]newPx=new int[width*height];
        bm.getPixels(oldPx,0,width,0,0,width,height);

        for(int i=0;i<width*height;i++){
            color=oldPx[i];
            r= Color.red(color);
            g=Color.green(color);
            b=Color.blue(color);
            a=Color.alpha(color);

            r=255-r;
            g=255-g;
            b=255-b;

            if(r>255){
                r=255;
            }else if(r<0){
                r=0;
            }

            if(g>255){
                g=255;
            }else if(g<0){
                g=0;
            }

            if(b>255){
                b=255;
            }else if(b<0){
                b=0;
            }

            newPx[i]=Color.argb(a,r,g,b);


        }

        bmp.setPixels(newPx,0,width,0,0,width,height);


        return  bmp;
    }

    /***
     * 老照片效果
     * @param bm
     * @return
     */

    public static Bitmap oldPhone(Bitmap bm){
        int width=bm.getWidth();
        int height=bm.getHeight();
        int color;
        int r,g,b,a;
        Bitmap bmp=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        int[]oldPx=new int[width*height];
        int[]newPx=new int[width*height];
        bm.getPixels(oldPx,0,width,0,0,width,height);

        for(int i=0;i<width*height;i++){
            color=oldPx[i];
            r= Color.red(color);
            g=Color.green(color);
            b=Color.blue(color);
            a=Color.alpha(color);

           r=(int)(0.393*r+0.769*g+0.189*b);
           g=(int)(0.349*r+0.686*g+0.168*b);
           b=(int)(0.272*r+0.534*g+0.131*b) ;

            if(r>255){
                r=255;
            }else if(r<0){
                r=0;
            }

            if(g>255){
                g=255;
            }else if(g<0){
                g=0;
            }

            if(b>255){
                b=255;
            }else if(b<0){
                b=0;
            }

            newPx[i]=Color.argb(a,r,g,b);


        }

        bmp.setPixels(newPx,0,width,0,0,width,height);


        return  bmp;
    }


    /**
     * 浮雕效果
     * @param bm
     * @return
     */

    public static Bitmap fuDiao(Bitmap bm){
        int width=bm.getWidth();
        int height=bm.getHeight();
        int color;
        int nextColor;
        int r,g,b,a;
        Bitmap bmp=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        int[]oldPx=new int[width*height];
        int[]newPx=new int[width*height];
        bm.getPixels(oldPx,0,width,0,0,width,height);

        for(int i=0;i<width*height;i++){
            color=oldPx[i];
            r= Color.red(color);
            g=Color.green(color);
            b=Color.blue(color);
            a=Color.alpha(color);

            if(i<width*height-1){
                nextColor=oldPx[i+1];

            }else{
                nextColor=oldPx[i];
            }


            int nextR=Color.red(nextColor);
            int nextG=Color.green(nextColor);
            int nextB=Color.blue(nextColor);
            int nextA=Color.alpha(nextColor);


            r=nextR-r+127;
            g=nextG-g+127;
            b=nextB-b+127;



            if(r>255){
                r=255;
            }else if(r<0){
                r=0;
            }

            if(g>255){
                g=255;
            }else if(g<0){
                g=0;
            }

            if(b>255){
                b=255;
            }else if(b<0){
                b=0;
            }

            newPx[i]=Color.argb(a,r,g,b);


        }

        bmp.setPixels(newPx,0,width,0,0,width,height);


        return  bmp;
    }


 public Bitmap setBitmapByColor(float[]color,Bitmap bm){
     Bitmap bmp=Bitmap.createBitmap(bm.getWidth(),bm.getHeight(), Bitmap.Config.ARGB_8888);
     android.graphics.ColorMatrix colorMatrix=new android.graphics.ColorMatrix();
     colorMatrix.set(color);

     Canvas canvas=new Canvas(bmp);
     Paint paint=new Paint();
     paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
     canvas.drawBitmap(bm,0,0,paint);
     return bmp;
 }


}
