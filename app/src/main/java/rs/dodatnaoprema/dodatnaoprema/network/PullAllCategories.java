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
import rs.dodatnaoprema.dodatnaoprema.models.categories.AllCategories;


public class PullAllCategories {

    public WebRequestCallbackInterface<AllCategories> webRequestCallbackInterface;
    private Context context;
    private String url;

    public PullAllCategories(Activity context) {
        this.context = context;
        webRequestCallbackInterface = null;
        url = Endpoints.getRequestUrlAllCategories();
    }

    public void setCallbackListener(WebRequestCallbackInterface<AllCategories> listener) {
        this.webRequestCallbackInterface = listener;
    }

    /**
     * function to pull list of all categories form web server
     */
    public void pullCategoriesList() {
        // Tag used to cancel the request
        String tag_string_req = "req_pull_all_categories";


        RequestQueue requestQueue = VolleySingleton.getsInstance(context).getRequestQueue();


        final GsonRequest<AllCategories> gsonRequest = new GsonRequest<AllCategories>(url, AllCategories.class, null, new Response.Listener<AllCategories>() {

            @Override
            public void onResponse(AllCategories categories) {

                if (categories != null) {

                    Log.logInfo("pullCategoriesResp:", "NOT NULL");
                    Log.logInfo("pullCategoriesResp:", categories.toString());


                    if (categories.getSuccess()) {
                        webRequestCallbackInterface.webRequestSuccess(true, categories);
                    } else {
                        webRequestCallbackInterface.webRequestSuccess(false, categories);
                    }
                } else {
                    Log.logDebug("pullCategoriesResp", "NULL RESPONSE");
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
                params.put("action", "povuciSveKategorijeUrl");
                params.put("id", url);
                return params;
            }
        };

        gsonRequest.setRetryPolicy(new DefaultRetryPolicy(AppConfig.DEFAULT_TIMEOUT_MS, AppConfig.DEFAULT_MAX_RETRIES, AppConfig.DEFAULT_BACKOFF_MULT));
        // Adding request to  queue
        requestQueue.add(gsonRequest);
    }

}
