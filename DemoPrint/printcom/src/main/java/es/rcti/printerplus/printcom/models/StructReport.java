package es.rcti.printerplus.printcom.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import es.rcti.printerplus.printcom.models.util.ImgTools;
import es.rcti.printerplus.printcom.models.util.SReportItem;

/**
 * Created by www.rcti.es on 26/08/2015.
 */
public class StructReport implements Parcelable, Serializable {

    private final static int IMAGE_MAX_WIDTH = 380;

    public final static int KEY_ALIGNMENT               = 30301;
    public final static int KEY_SIZE_FONT_W             = 30302;
    public final static int KEY_SIZE_FONT_H             = 30303;
    public final static int KEY_LINE_SPACE              = 30304;
    public final static int KEY_TEXT_STYLE_UNDERLINE    = 30305;
    public final static int KEY_TEXT_STYLE_BOLD         = 30306;
    public final static int KEY_TEXT                    = 30307;
    public final static int KEY_TEXT_REVERSE_MODE       = 30308;
    public final static int KEY_TEXT_X_POSITION         = 30309;
    public final static int KEY_FEED_UNIT               = 30310;
    public final static int KEY_IMAGE_PATH              = 30311;
    public final static int KEY_IMAGE_MODE              = 30312;
    public final static int KEY_IMAGE_HALFTONE          = 30313;
    public final static int KEY_IMAGE_BRIGHTNESS        = 30314;
    public final static int KEY_CUT_TYPE                = 30315;
    public final static int KEY_CUT                     = 30316;
    public final static int KEY_BARCODE_DATA            = 30317;
    public final static int KEY_BARCODE_TYPE            = 30318;
    public final static int KEY_BARCODE_HRI             = 30319;
    public final static int KEY_BARCODE_WIDTH           = 30320;
    public final static int KEY_BARCODE_HEIGHT          = 30321;
    public final static int KEY_OPEN_DRAWER             = 30322;
    public final static int KEY_FONT                    = 30323;
    public final static int KEY_COLOR                   = 30324;
    public final static int KEY_TEXT_LANG               = 30325;

    private ArrayList<SReportItem> items;

    public StructReport() {
        this.items = new ArrayList<SReportItem>();
    }

    public ArrayList<SReportItem> getItems() {
        return items;
    }

    public void freeMem() {
        items.clear();
        items = null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(items);
    }

    public static final Creator<StructReport> CREATOR = new Creator<StructReport>() {
        public StructReport createFromParcel(Parcel in) {
            return new StructReport(in);
        }

        public StructReport[] newArray(int size) {
            return new StructReport[size];
        }
    };

    private StructReport(Parcel in) {
        items = new ArrayList<SReportItem>();
        in.readTypedList(items, SReportItem.CREATOR);
    }

    /**
     * This method lets you to set the ALIGNMENT for Image, Text and Barcode.
     * @param alignmentType ALIGNMENT_LEFT, ALIGNMENT_CENTER, ALIGNMENT_RIGHT
     */
    public void addItemAlignment(int alignmentType) {
        String auxArray[] = {String.valueOf(alignmentType)};
        items.add(new SReportItem(KEY_ALIGNMENT, auxArray));
    }

    /**
     * This method lets you to set the FONT SIZE, it contains the width and height.
     * @param charWidth SIZE_FONT_1 to SIZE_FONT_8
     */
    public void addItemSizeFont(int charWidth) {
        String auxArray[] = {String.valueOf(charWidth)};
        items.add(new SReportItem(KEY_SIZE_FONT_W, auxArray));
    }

    /**
     * This method lets you to set LINE SPACING, by default is 30.
     * @param dpSize 0 .. 30 .. X
     */
    public void addLineSpace(int dpSize) {
        String auxArray[] = {String.valueOf(dpSize)};
        items.add(new SReportItem(KEY_LINE_SPACE,  auxArray));
    }

    /**
     * This method lets you to enable or disable UNDERLINE for text.
     * @param enabled true to enable
     */
    public void addTextUnderlined(boolean enabled) {
        String auxArray[] = {String.valueOf(enabled)};
        items.add(new SReportItem(KEY_TEXT_STYLE_UNDERLINE,  auxArray));
    }

    /**
     * This method lets you to enable or disable BOLD for text.
     * @param enabled true to enable
     */
    public void addTextBold(boolean enabled) {
        String auxArray[] = {String.valueOf(enabled)};
        items.add(new SReportItem(KEY_TEXT_STYLE_BOLD,  auxArray));
    }

    /**
     * This method lets you to enable or disable REVERSE MODE for text.
     * @param reverse true to enable
     */
    public void addTextReverseMode(boolean reverse) {
        String auxArray[] = {String.valueOf(reverse)};
        items.add(new SReportItem(KEY_TEXT_REVERSE_MODE, auxArray));
    }

    /**
     * This method lets you to set (AXIS X) horizontal starting position to print.
     * @param xposition 0 .. x  .. n column to start to print
     */
    public void addTextPosition(int xposition) {
        String auxArray[] = {String.valueOf(xposition)};
        items.add(new SReportItem(KEY_TEXT_X_POSITION, auxArray));
    }

    /**
     * This method lets you to add a TEXT MESSAGE to be printed.
     * @param text message to print
     */
    public void addText(String text) {
        String auxArray[] = {text};
        items.add(new SReportItem(KEY_TEXT, auxArray));
    }

    /**
     * This method lets you to set (AXIS Y) FEED unit, by default is 30.
     * @param dpSize 0 .. 30 .. X
     */
    public void addFeedUnit(int dpSize) {
        String auxArray[] = {String.valueOf(dpSize)};
        items.add(new SReportItem(KEY_FEED_UNIT,  auxArray));
    }

    /**
     * This method lets you to add an IMAGE to be printed.
     * @param mbmp a Bitmap object
     */
    public void addImageBitmap(Bitmap mbmp) {
        Bitmap bmp = ImgTools.getResizedBitmap(mbmp, IMAGE_MAX_WIDTH);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        String auxArray[] = {"nopath", String.valueOf(bmp.getWidth()), String.valueOf(bmp.getHeight())};

        items.add(new SReportItem(KEY_IMAGE_PATH, auxArray, byteArray));
    }

    /**
     * This method lets you to add an IMAGE to be sent to print.
     * @param path public full path location from a image file
     */
    public void addImagePath(String path) {
        Bitmap bmp = BitmapFactory.decodeFile(path);

        bmp = ImgTools.getResizedBitmap(bmp, IMAGE_MAX_WIDTH);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        String auxArray[] = {path, String.valueOf(bmp.getWidth()), String.valueOf(bmp.getHeight())};

        items.add(new SReportItem(KEY_IMAGE_PATH, auxArray, byteArray));
    }

    /**
     * This method lets you to set IMAGE MODE for color.
     * @param mode IMAGE_MODE_MONO or IMAGE_MODE_GRAY16
     */
    public void addImageMode(int mode) {
        String auxArray[] = {String.valueOf(mode)};
        items.add(new SReportItem(KEY_IMAGE_MODE, auxArray));
    }

    /**
     * This method lets you to set IMAGE HALFTONE or texture.
     * @param halftone IMAGE_HALFTONE_DITHER or IMAGE_HALFTONE_ERROR_DIFUSION or IMAGE_HALFTONE_THRESHOLD
     */
    public void addImageHalftone(int halftone) {
        String auxArray[] = {String.valueOf(halftone)};
        items.add(new SReportItem(KEY_IMAGE_HALFTONE, auxArray));
    }

    /**
     * This method lets you to enable MODE CUT AND FEED or by disabling only applies CUT.
     * @param cutAndFeed true if cut and feed.
     */
    public void addCutType(boolean cutAndFeed) {
        String auxArray[] = {String.valueOf(cutAndFeed)};
        items.add(new SReportItem(KEY_CUT_TYPE,  auxArray));
    }

    /**
     * This method lets you to add an order for CUT to be executed.
     * Sends order to cut paper
     */
    public void addCut() {
        String auxArray[] = {"0"};
        items.add(new SReportItem(KEY_CUT,  auxArray));
    }

    /**
     * This method lets you to add FONT, it could be:
     * FONT_A, FONT_B, FONT_C, FONT_D, FONT_E
     * @param typeFont FONT_X
     */
    public void addFont(int typeFont) {
        String auxArray[] = {String.valueOf(typeFont)};
        items.add(new SReportItem(KEY_FONT, auxArray));
    }

    /**
     * USELESS METHOD (DEPRECATED)
     * Now it is not necessary, because the printer receives the unicode text format and
     * draws on their respective char map.
     *
     * This method lets you to set lang or code page to print, it could be:
     * LANG_EN, LANG_JA, LANG_KO, LANG_TH, LANG_VI, LANG_ZH_CN or LANG_ZH_TW.
     * @param typeLang LANG_XX
     */
    public void addTextLang(int typeLang) {
        String auxArray[] = {String.valueOf(typeLang)};
        items.add(new SReportItem(KEY_TEXT_LANG, auxArray));
    }

    /**
     * This method lets you to set the color to be selected to print
     * it could be:
     * COLOR_1, COLOR_2, COLOR_3, COLOR_4
     * depends on printer colors allowed.
     * @param typeColor COLOR_X
     */
    public void addColor(int typeColor) {
        String auxArray[] = {String.valueOf(typeColor)};
        items.add(new SReportItem(KEY_COLOR, auxArray));
    }

    /**
     * This method lets you to set BARCODE TYPE
     * it coudl be:
     * BARCODE_UPC_A, BARCODE_UPC_E, BARCODE_EAN13, BARCODE_JAN13, BARCODE_EAN8,
     * BARCODE_JAN8, BARCODE_CODE39, BARCODE_ITF, BARCODE_CODABAR, BARCODE_CODE93,
     * BARCODE_CODE128, BARCODE_GS1_128, BARCODE_GS1_DATABAR_OMNIDIRECTIONAL,
     * BARCODE_GS1_DATABAR_TRUNCATED, BARCODE_GS1_DATABAR_LIMITED or
     * BARCODE_GS1_DATABAR_EXPANDED.
     * @param typeBarcode BARCODE_XXXX
     */
    public void addBarcodeType(int typeBarcode) {
        String auxArray[] = {String.valueOf(typeBarcode)};
        items.add(new SReportItem(KEY_BARCODE_TYPE, auxArray));
    }

    /**
     * This method lets you to set BARCODE HUMAN READABLE INFORMATION
     * it could be:
     * BARCODE_HRI_ABOVE to enable write the information above of barcode
     * BARCODE_HRI_BELOW to enable write the information below of barcode
     * BARCODE_HRI_BOTH to enable write the information above and below of barcode
     * BARCODE_HRI_NONE to disable write the information of barcode
     * @param typeHRI BARCODE_HRI_XXXX
     */
    public void addBarcodeHRI(int typeHRI) {
        String auxArray[] = {String.valueOf(typeHRI)};
        items.add(new SReportItem(KEY_BARCODE_HRI, auxArray));
    }

    /**
     * This method lets you to set a BARCODE WIDTH, by default is 2.
     * @param width [2 .. 5]
     */
    public void addBarcodeWidth(int width){
        String auxArray[] = {String.valueOf(width)};
        items.add(new SReportItem(KEY_BARCODE_WIDTH, auxArray));
    }

    /**
     * This method lets you to set a BARCODE HEIGHT, by default is 50.
     * @param height [30 .. 300]
     */
    public void addBarcodeHeight(int height) {
        String auxArray[] = {String.valueOf(height)};
        items.add(new SReportItem(KEY_BARCODE_HEIGHT, auxArray));
    }

    /**
     * This method lets you to add a text BARCODE to be printed.
     * @param data info[data] to generate a barcode
     */
    public void addBarcodeData(String  data) {
        String auxArray[] = {String.valueOf(data)};
        items.add(new SReportItem(KEY_BARCODE_DATA, auxArray));
    }

    /**
     * This method lets you to add a OPEN DRAWER order to be executed.
     */
    public void addOpenDrawer() {
        String auxArray[] = {String.valueOf("")};
        items.add(new SReportItem(KEY_OPEN_DRAWER, auxArray));
    }

    /*
     Customized keys
     */
    public final static int ALIGNMENT_LEFT = 90001;
    public final static int ALIGNMENT_CENTER = 90002;
    public final static int ALIGNMENT_RIGHT = 90003;

    public final static int FONT_A = 0;
    public final static int FONT_B = 1;
    public final static int FONT_C = 2;
    public final static int FONT_D = 3;
    public final static int FONT_E = 4;

    public final static int COLOR_1 = 0;
    public final static int COLOR_2 = 1;
    public final static int COLOR_3 = 2;
    public final static int COLOR_4 = 3;


    public final static int SIZE_FONT_1 = 1;
    public final static int SIZE_FONT_2 = 2;
    public final static int SIZE_FONT_3 = 3;
    public final static int SIZE_FONT_4 = 4;
    public final static int SIZE_FONT_5 = 5;
    public final static int SIZE_FONT_6 = 6;
    public final static int SIZE_FONT_7 = 7;
    public final static int SIZE_FONT_8 = 8;

    public static final int BARCODE_HRI_ABOVE   =1;
    public static final int BARCODE_HRI_BELOW   =2;
    public static final int BARCODE_HRI_BOTH    =3;
    public static final int BARCODE_HRI_NONE    =0;

    public static final int BARCODE_UPC_A                       = 70000;
    public static final int BARCODE_UPC_E                       = 70001;
    public static final int BARCODE_EAN13                       = 70002;
    public static final int BARCODE_JAN13                       = 70003;
    public static final int BARCODE_EAN8                        = 70004;
    public static final int BARCODE_JAN8                        = 70005;
    public static final int BARCODE_CODE39                      = 70006;
    public static final int BARCODE_ITF                         = 70007;
    public static final int BARCODE_CODABAR                     = 70008;
    public static final int BARCODE_CODE93                      = 70009;
    public static final int BARCODE_CODE128                     = 70010;
    public static final int BARCODE_GS1_128                     = 70011;
    public static final int BARCODE_GS1_DATABAR_OMNIDIRECTIONAL = 70012;
    public static final int BARCODE_GS1_DATABAR_TRUNCATED       = 70013;
    public static final int BARCODE_GS1_DATABAR_LIMITED         = 70014;
    public static final int BARCODE_GS1_DATABAR_EXPANDED        = 70015;

    public static final int IMAGE_MODE_MONO = 1;
    public static final int IMAGE_MODE_GRAY16 = 0;

    public static final int IMAGE_HALFTONE_DITHER = 0;
    public static final int IMAGE_HALFTONE_ERROR_DIFUSION = 1;
    public static final int IMAGE_HALFTONE_THRESHOLD = 2;

    public static final int LANG_EN = 0;
    public static final int LANG_JA = 1;
    public static final int LANG_KO = 2;
    public static final int LANG_TH = 3;
    public static final int LANG_VI = 4;
    public static final int LANG_ZH_CN = 5;
    public static final int LANG_ZH_TW = 6;
}