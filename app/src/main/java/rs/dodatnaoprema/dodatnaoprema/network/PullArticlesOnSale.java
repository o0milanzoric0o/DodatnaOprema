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
import rs.dodatnaoprema.dodatnaoprema.models.sales.ArtikliNaAkciji;


public class PullArticlesOnSale {

    public WebRequestCallbackInterface<ArtikliNaAkciji> webRequestCallbackInterface;
    private Context context;

    public PullArticlesOnSale(Activity context) {
        this.context = context;
        webRequestCallbackInterface = null;
    }

    public void setCallbackSalesListener(WebRequestCallbackInterface<ArtikliNaAkciji> listener) {
        this.webRequestCallbackInterface = listener;
    }

    /**
     * function to pull list of articles on sale form web server
     */
    public void pullSalesList(final String url) {
        // Tag used to cancel the request
        String tag_string_req = "req_pull_articles_on_sale";


        RequestQueue requestQueue = VolleySingleton.getsInstance(context).getRequestQueue();


        final GsonRequest<ArtikliNaAkciji> gsonRequest = new GsonRequest<ArtikliNaAkciji>(url, ArtikliNaAkciji.class, null, new Response.Listener<ArtikliNaAkciji>() {

            @Override
            public void onResponse(ArtikliNaAkciji articles) {

                if (articles != null) {
                    Log.logInfo("pullSalesResp:", "Nije null");
                    Log.logInfo("pullSalesResp:", articles.toString());


                    if (articles.getSuccess()) {
                        webRequestCallbackInterface.webRequestSuccess(true, articles);
                    } else {
                        webRequestCallbackInterface.webRequestSuccess(false, articles);
                    }
                } else {
                    Log.logDebug("pullSalesResp", "NULL RESPONSE");
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
                params.put("action", "povuciArtikleNaAkciji");
                params.put("id", url);
                return params;
            }
        };

        gsonRequest.setRetryPolicy(new DefaultRetryPolicy(AppConfig.DEFAULT_TIMEOUT_MS, AppConfig.DEFAULT_MAX_RETRIES, AppConfig.DEFAULT_BACKOFF_MULT));
        // Adding request to  queue
        requestQueue.add(gsonRequest);
    }


}
