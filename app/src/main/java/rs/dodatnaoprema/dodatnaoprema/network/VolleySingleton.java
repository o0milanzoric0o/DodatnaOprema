package rs.dodatnaoprema.dodatnaoprema.network;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
    public static final String TAG = VolleySingleton.class.getSimpleName();
    private RequestQueue mRequestQueue;
    private static VolleySingleton sInstance = null;
    private static Context mCtx;

    private VolleySingleton(Context context) {
        mCtx = context;
        mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized VolleySingleton getsInstance(Context context) {
        if (sInstance == null) {
            sInstance = new VolleySingleton(context);
        }

        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        // VolleyLog.e("Adding request to queue: %s", req.getUrl());
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);

        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
