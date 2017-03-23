package es.rcti.printerplus.printcom.models.util;

import android.graphics.Bitmap;

/**
 * Created by www.rcti.es on 21/09/2015.
 */
public class ImgTools {

    public static Bitmap getResizedBitmap(Bitmap bmp, int maxWidth) {
        Bitmap nBmp = bmp;
        float scale = (float)bmp.getWidth()/maxWidth;
        int width, height;
        width = bmp.getWidth();
        height = bmp.getHeight();
        if (scale > 1.0) {
            width = ((int)(width / scale));
            height = ((int) (height / scale));
            nBmp = Bitmap.createScaledBitmap(nBmp, width, height, true);
        }
        return nBmp;
    }

}
