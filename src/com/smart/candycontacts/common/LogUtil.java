
package com.smart.candycontacts.common;

import android.util.Log;

/**
 * LogUtils for candyContacts
 *
 * @author Elvis
 *
 */
public class LogUtil {
    private static final boolean DEBUG = true;

    private static final String TAG = "candyContacts";

    public static void log(String msg) {
        if (DEBUG) {
            Log.i(TAG, msg);
        }
    }

    public static void log(String tag, String msg) {
        if (DEBUG) {
            Log.i(tag, msg);
        }
    }

}
