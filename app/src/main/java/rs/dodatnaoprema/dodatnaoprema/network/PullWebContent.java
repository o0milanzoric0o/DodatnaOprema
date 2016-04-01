package rs.dodatnaoprema.dodatnaoprema.network;

import android.app.Activity;
import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.Map;

import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.common.utils.Log;


public class PullWebContent<T> {

    public WebRequestCallbackInterface<T> webRequestCallbackInterface;
    private Context context;
    private String url;
    private Class<T> t;
    private RequestQueue requestQueue;


    public PullWebContent(Activity context, Class<T> mClass, String url, RequestQueue requestQueue) {

        this.context = context;
        webRequestCallbackInterface = null;
        this.url = url;
        this.t = mClass;
        this.requestQueue = requestQueue;

    }

    public void setCallbackListener(WebRequestCallbackInterface<T> listener) {
        this.webRequestCallbackInterface = listener;

    }

    /**
     * function to pull list of all categories form web server
     */
    public void pullList() {
        // Tag used to cancel the request
        String tag_string_req = "req_" + url;


        final GsonRequest<T> gsonRequest = new GsonRequest<T>(url, t, null, new Response.Listener<T>() {

            @Override
            public void onResponse(T categories) {

                if (categories != null) {
                    webRequestCallbackInterface.webRequestSuccess(true, categories);
                    Log.logInfo("tag_string_req", "NOT NULL");
                    Log.logInfo("tag_string_req", categories.toString());

                } else {
                    Log.logDebug("pullCategoriesResp", "NULL RESPONSE");
                    webRequestCallbackInterface.webRequestSuccess(false, categories);
                }

            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                webRequestCallbackInterface.webRequestError(error.getMessage());

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Post params
                Map<String, String> params = new HashMap<>();
                params.put("action", "pull " + t.getName());
                params.put("id", url);
                return params;
            }
        };

        gsonRequest.setRetryPolicy(new DefaultRetryPolicy(AppConfig.DEFAULT_TIMEOUT_MS, AppConfig.DEFAULT_MAX_RETRIES, AppConfig.DEFAULT_BACKOFF_MULT));
        // Adding request to  queue
        requestQueue.add(gsonRequest);
    }
}
