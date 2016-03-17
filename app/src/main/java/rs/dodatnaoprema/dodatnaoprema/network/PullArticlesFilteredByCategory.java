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
import rs.dodatnaoprema.dodatnaoprema.models.articles.articles_filtered_by_category.ArticlesFilteredByCategory;


public class PullArticlesFilteredByCategory {
    public WebRequestCallbackInterface<ArticlesFilteredByCategory> webRequestCallbackInterface;
    private Context context;
    private String url;


    public PullArticlesFilteredByCategory(Activity context, int id, int from, int to, String currency, String language, int brand, int sort) {

        this.context = context;
        webRequestCallbackInterface = null;
        url = Endpoints.getRequestUrlSearchArticlesByCategory(id, from, to, currency, language, brand, sort);
        Log.logInfo("pullCategoriesByIDResp:", url);


    }

    public void setCallbackListener(WebRequestCallbackInterface<ArticlesFilteredByCategory> listener) {
        this.webRequestCallbackInterface = listener;
    }

    /**
     * function to pull articles filtered by category from server
     */
    public void pullArticlesList() {
        // Tag used to cancel the request
        String tag_string_req = "req_pull_all_categories";


        RequestQueue requestQueue = VolleySingleton.getsInstance(context).getRequestQueue();


        final GsonRequest<ArticlesFilteredByCategory> gsonRequest = new GsonRequest<ArticlesFilteredByCategory>(url, ArticlesFilteredByCategory.class, null, new Response.Listener<ArticlesFilteredByCategory>() {

            @Override
            public void onResponse(ArticlesFilteredByCategory articles) {

                if (articles != null) {

                    Log.logInfo("pullCategoriesResp:", "NOT NULL");
                    Log.logInfo("pullCategoriesResp:", articles.toString());


                    if (articles.getSuccess()) {
                        webRequestCallbackInterface.webRequestSuccess(true, articles);
                    } else {
                        webRequestCallbackInterface.webRequestSuccess(false, articles);
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
