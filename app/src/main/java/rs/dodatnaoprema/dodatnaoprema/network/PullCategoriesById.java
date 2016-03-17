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
import rs.dodatnaoprema.dodatnaoprema.models.categories.categories_by_id.CategoriesByID;


public class PullCategoriesById {

    public WebRequestCallbackInterface<CategoriesByID> webRequestCallbackInterface;
    private Context context;
    private String url;


    public PullCategoriesById(Activity context, int id) {

        this.context = context;
        webRequestCallbackInterface = null;
        url = Endpoints.getRequestUrlCategoriesById(id);

    }

    public void setCallbackListener(WebRequestCallbackInterface<CategoriesByID> listener) {
        this.webRequestCallbackInterface = listener;
    }

    /**
     * function to pull list of all categories filtered by id form web server
     */
    public void pullCategoriesFilteredByIDList() {
        // Tag used to cancel the request
        String tag_string_req = "req_pull_all_categories";


        RequestQueue requestQueue = VolleySingleton.getsInstance(context).getRequestQueue();


        final GsonRequest<CategoriesByID> gsonRequest = new GsonRequest<CategoriesByID>(url, CategoriesByID.class, null, new Response.Listener<CategoriesByID>() {

            @Override
            public void onResponse(CategoriesByID categories) {

                if (categories != null) {

                    Log.logInfo("pullCategoriesByIDResp:", "NOT NULL");
                    Log.logInfo("pullCategoriesByIDResp:", categories.toString());


                    if (categories.getSuccess()) {
                        webRequestCallbackInterface.webRequestSuccess(true, categories);
                    } else {
                        webRequestCallbackInterface.webRequestSuccess(false, categories);
                    }
                } else {
                    Log.logDebug("pullCategoriesByIDResp", "NULL RESPONSE");
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
                params.put("action", "povuciSveKategorijePoIDuUrl");
                params.put("id", url);
                return params;
            }
        };

        gsonRequest.setRetryPolicy(new DefaultRetryPolicy(AppConfig.DEFAULT_TIMEOUT_MS, AppConfig.DEFAULT_MAX_RETRIES, AppConfig.DEFAULT_BACKOFF_MULT));
        // Adding request to  queue
        requestQueue.add(gsonRequest);
    }

}
