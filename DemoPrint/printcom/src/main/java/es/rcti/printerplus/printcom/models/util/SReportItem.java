package es.rcti.printerplus.printcom.models.util;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by www.rcti.es on 30/08/2015.
 */
public class SReportItem implements Parcelable, Serializable {

    private int idKey;
    private String params[];
    private byte lbytes[];

    public SReportItem(int keyID, String params2KEY[]) {
        this.idKey = keyID;
        this.params = params2KEY;
        this.lbytes = new byte[1];
    }

    public SReportItem(int keyID, String params2KEY[], byte byteArray[]) {
        this.idKey = keyID;
        this.params = params2KEY;
        this.lbytes = byteArray;
    }

    public byte[] getLbytes() {
        return lbytes;
    }

    public void setLbytes(byte[] lbytes) {
        this.lbytes = lbytes;
    }

    public int getIdKey() {
        return idKey;
    }

    public void setIdKey(int idKey) {
        this.idKey = idKey;
    }

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(idKey);
        parcel.writeInt(params.length);
        for (int index = 0; index < params.length; ++index) {
            parcel.writeString(params[index]);
        }
        parcel.writeInt(lbytes.length);
        parcel.writeByteArray(lbytes);
    }

    public static final Creator<SReportItem> CREATOR = new Creator<SReportItem>() {
        public SReportItem createFromParcel(Parcel in) {
            return new SReportItem(in);
        }

        public SReportItem[] newArray(int size) {
            return new SReportItem[size];
        }
    };

    private SReportItem(Parcel in) {
        this.idKey = in.readInt();
        int size = in.readInt();
        params = new String[size];
        for (int j = 0; j < size; ++j) {
            params[j] = in.readString();
        }
        int blen = in.readInt();
        this.lbytes = new byte[blen];
        in.readByteArray(this.lbytes);
    }
}