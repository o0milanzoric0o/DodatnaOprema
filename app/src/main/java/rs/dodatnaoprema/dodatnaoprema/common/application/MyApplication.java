package rs.dodatnaoprema.dodatnaoprema.common.application;

import android.app.Application;

import rs.dodatnaoprema.dodatnaoprema.gcm.MyPreferenceManager;

/**
 * Created by 1 on 3/8/2016.
 */
public class MyApplication extends Application {
    public static final String TAG = MyApplication.class
            .getSimpleName();

    private static MyApplication mInstance;

    private MyPreferenceManager pref;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }


    public MyPreferenceManager getPrefManager() {
        if (pref == null) {
            pref = new MyPreferenceManager(this);
        }

        return pref;
    }
}
