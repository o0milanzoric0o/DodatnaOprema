package rs.dodatnaoprema.dodatnaoprema.utils;

import android.util.Log;

public class AppConfig {



    public static final int ACTIVITY_REQ_SETTINGS = 99;
    public static final int ACTIVITY_RESP_SETTINGS_UPDATE = 98;

    /**
     * The default socket timeout in milliseconds
     */
    public static final int DEFAULT_TIMEOUT_MS = 3000;

    /**
     * The default number of retries
     */
    public static final int DEFAULT_MAX_RETRIES = 4;

    /**
     * The default backoff multiplier
     */
    public static final float DEFAULT_BACKOFF_MULT = 1f;

    public static boolean debug = true;

    public static void logDebug(String tag, String msg) {
        if (debug)
            Log.d(tag, msg);
    }

    public static void logInfo(String tag, String msg) {
        if (debug)
            Log.i(tag, msg);
    }

}
