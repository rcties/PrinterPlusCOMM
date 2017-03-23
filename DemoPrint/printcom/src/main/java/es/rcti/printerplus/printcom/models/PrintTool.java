package es.rcti.printerplus.printcom.models;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import es.rcti.printerplus.printcom.models.util.Keys;

/**
 * Created by www.rcti.es on 18/03/2017.
 */

public class PrintTool {

    public static void sendOrder(Context ctx, StructReport msr ) {
        Intent mIntent = new Intent();
        mIntent.setComponent(new ComponentName(Keys.APP_ID, Keys.APP_SERVICE));

        mIntent.putExtra(Keys.KEY_SEND_TO_PRINT, true);
        mIntent.putExtra(Keys.KEY_STRUCT_REPORT, ((Parcelable) msr));

        ctx.startService( mIntent );
    }

}
