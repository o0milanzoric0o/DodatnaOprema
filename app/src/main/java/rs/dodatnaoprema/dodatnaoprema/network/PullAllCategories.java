package rs.dodatnaoprema.dodatnaoprema.network;

import android.app.Activity;
import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.Map;

import rs.dodatnaoprema.dodatnaoprema.models.categories.SveKategorije;
import rs.dodatnaoprema.dodatnaoprema.utils.AppConfig;


public class PullAllCategories {

    public WebRequestCallbackInterface webRequestCallbackInterface;
    private Context context;

    public PullAllCategories(Activity context) {
        this.context = context;
        webRequestCallbackInterface = null;
    }

    public void setCallbackListener(WebRequestCallbackInterface listener) {
        this.webRequestCallbackInterface = listener;
    }

    /**
     * function to pull list of all categories form web server
     */
    public void pullCategoriesList(final String url) {
        // Tag used to cancel the request
        String tag_string_req = "req_pull_all_categories";


        RequestQueue requestQueue = VolleySingleton.getsInstance(context).getRequestQueue();


        final GsonRequest gsonRequest = new GsonRequest(url, SveKategorije.class, null, new Response.Listener<SveKategorije>() {

            @Override
            public void onResponse(SveKategorije categories) {

                if (categories != null) {
                    AppConfig.logInfo("pullCategoriesResp:", "Nije null");
                    AppConfig.logInfo("pullCategoriesResp:", categories.toString());


                if (categories.getSuccess()) {
                    webRequestCallbackInterface.webRequestSuccess(true, categories);
                } else {
                    webRequestCallbackInterface.webRequestSuccess(false, categories);
                }
                } else {
                    AppConfig.logDebug("pullCategoriesResp", "NULL RESPONSE");
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
                params.put("action", "povuciSensorUid");
                params.put("id", url);
                return params;
            }
        };

        gsonRequest.setRetryPolicy(new DefaultRetryPolicy(AppConfig.DEFAULT_TIMEOUT_MS, AppConfig.DEFAULT_MAX_RETRIES, AppConfig.DEFAULT_BACKOFF_MULT));
        // Adding request to  queue
        requestQueue.add(gsonRequest);
    }
}
