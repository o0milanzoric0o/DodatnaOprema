package rs.dodatnaoprema.dodatnaoprema.common.utils;

/**
 * ******************************
 * Created by 1 on 3/7/2016.
 * ******************************
 */

/* TODO
* Figure out better way to turn logging on/off. Maybe using gradle script to check  for
* selected build configuration?
* */

public class Log {
    public static boolean debug = true;

    public static void logDebug(String tag, String msg) {
        if (debug)
            android.util.Log.d(tag, msg);
    }

    public static void logInfo(String tag, String msg) {
        if (debug)
            android.util.Log.i(tag, msg);
    }

}
