package es.rcti.printerplus.printcom.models.util;

import java.io.Serializable;

/**
 * Created by Jose on 22/11/2016.
 */
public class PrinterSettings implements Serializable {
    private static final long serialVersionUID = -29238982928391L;
    private int mWidth;

    public PrinterSettings() {
        this.mWidth = 80;
    }

    public int getmWidth() {
        return mWidth;
    }

    public void setmWidth(int mWidth) {
        this.mWidth = mWidth;
    }

}
