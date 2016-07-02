package adapter;

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
    public static float[]yuantu={1,0,0,0,0,
            0,1,0,0,0,
            0,0,1,0,0,
            0,0,0,1,0};

    public static float[]huidu={0.33f,0.59f,0.11f,0,0,
            0.33f,0.59f,0.11f,0,0,
            0.33f,0.59f,0.11f,0,0,
            0,0,0,1,0};

    public static float[]fanzhuan={-1,0,0,1,1,
            0,-1,0,1,1,
            0,0,-1,1,1,
            0,0,0,1,0};

    public static float[]huaijiu={0.393f,0.769f,0.189f,0,0,
            0.349f,0.686f,0.168f,0,0,
            0.272f,0.534f,0.131f,0,0,
            0,0,0,1,0};

    public static float[]quse={1.5f,1.5f,1.5f,0,-1,
            1.5f,1.5f,1.5f,0,-1,
            1.5f,1.5f,1.5f,0,-1,
            0,0,0,1,0};

    public static float[]gaobaohe={1.438f,-0.122f,-0.016f,0,-0.03f,
            -0.062f,1.378f,-0.016f,0,0.05f,
            -0.062f,-0.122f,1.438f,0,-0.02f,
            0,0,0,1,0};

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

    public  Bitmap handleImageNegative(Bitmap bm){
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

    public  Bitmap oldPhone(Bitmap bm){
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

    public  Bitmap fuDiao(Bitmap bm){
        int width=bm.getWidth();
        int height=bm.getHeight();
        int color;
        int nextColor;
        int r,g,b,a;
        Bitmap bmp=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        int[]oldPx=new int[width*height];
        int[]newPx=new int[width*height];
        bm.getPixels(oldPx, 0, width, 0, 0, width, height);

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

        bmp.setPixels(newPx, 0, width, 0, 0, width, height);


        return  bmp;
    }


 public Bitmap setBitmapByColor(float[]color,Bitmap bm){
     Bitmap bmp=Bitmap.createBitmap(bm.getWidth(),bm.getHeight(), Bitmap.Config.ARGB_8888);
     ColorMatrix colorMatrix=new ColorMatrix();
     colorMatrix.set(color);

     Canvas canvas=new Canvas(bmp);
     Paint paint=new Paint();
     paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
     canvas.drawBitmap(bm, 0, 0, paint);
     return bmp;
 }


    int[] getGray(int[] pixels, int width, int height)
    {
        int gray[] = new int[width * height];
        for (int i = 0; i < width - 1; i++)
        {
            for (int j = 0; j < height - 1; j++)
            {
                int index = width * j + i;
                int rgba = pixels[index];
                int g = ((rgba & 0x00FF0000) >> 16) * 3 + ((rgba & 0x0000FF00) >> 8) * 6 + ((rgba & 0x000000FF)) * 1;
                gray[index] = g / 10;
            }
        }

        return gray;
    }


    public int[] getInverse(int[] gray)
    {
        int[] inverse = new int[gray.length];

        for (int i = 0, size = gray.length; i < size; i++)
        {
            inverse[i] = 255 - gray[i];
        }
        return inverse;
    }

    public int[] guassBlur(int[] inverse, int width, int height)
    {
        int[] guassBlur = new int[inverse.length];

        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                int temp = width * (j) + (i);
                if ((i == 0) || (i == width - 1) || (j == 0) || (j == height - 1))
                {
                    guassBlur[temp] = 0;
                }
                else
                {
                    int i0 = width * (j - 1) + (i - 1);
                    int i1 = width * (j - 1) + (i);
                    int i2 = width * (j - 1) + (i + 1);
                    int i3 = width * (j) + (i - 1);
                    int i4 = width * (j) + (i);
                    int i5 = width * (j) + (i + 1);
                    int i6 = width * (j + 1) + (i - 1);
                    int i7 = width * (j + 1) + (i);
                    int i8 = width * (j + 1) + (i + 1);

                    int sum = inverse[i0] + 2 * inverse[i1] + inverse[i2] + 2 * inverse[i3] + 4 * inverse[i4] + 2 * inverse[i5] + inverse[i6] + 2 * inverse[i7] + inverse[i8];

                    sum /= 16;

                    guassBlur[temp] = sum;
                }
            }
        }
        return guassBlur;
    }


    public int[] deceasecolorCompound(int[] guassBlur, int[] gray, int width, int height)
    {
        int a, b, temp;
        float ex;
        int[] output = new int[guassBlur.length];

        for(int i=0 ; i< width ; i++)
        {
            for(int j=0 ; j<height ; j++)
            {
                int index = j*width + i;
                b = guassBlur[index];
                a = gray[index];

                temp = a+a*b/(256-b);
                ex = temp*temp*1.0f/255/255;
                temp = (int) (temp *ex);

                a = Math.min(temp, 255);

                output[index] = a;
            }
        }
        return output;
    }


    public Bitmap create(int[] pixels , int[] output,int width , int height)
    {
        for(int i=0 ,size = pixels.length ; i<size ; i++)
        {
            int gray = output[i];
            int pixel = (pixels[i] & 0xff000000) | (gray<<16) | (gray<< 8) | gray;//注意加上原图的 alpha通道

            output[i] = pixel;
        }

        return Bitmap.createBitmap(output, width, height, Bitmap.Config.ARGB_8888);
    }


    public Bitmap createPencli(Bitmap bitmap)
    {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);

        int[] gray = getGray(pixels, width, height);
        int[] inverse = getInverse(gray);

        int[] guassBlur = guassBlur(inverse, width, height);

        int[] output = deceasecolorCompound(guassBlur , gray , width , height);

        return create(pixels, output, width, height);
    }

}
