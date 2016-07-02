package adapter;

import android.graphics.Bitmap;

/**
 * Created by 张佳亮 on 2016/2/15.
 */
public class Photo {
    private Bitmap bitmap;
    private String description;

    public Photo(Bitmap bitmap, String description) {
        this.bitmap = bitmap;
        this.description = description;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
