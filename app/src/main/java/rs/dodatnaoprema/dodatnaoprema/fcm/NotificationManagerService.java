package rs.dodatnaoprema.dodatnaoprema.fcm;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import rs.dodatnaoprema.dodatnaoprema.OneArticleActivity;
import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.models.one_article.OneArticle;
import rs.dodatnaoprema.dodatnaoprema.network.PullWebContent;
import rs.dodatnaoprema.dodatnaoprema.network.UrlEndpoints;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;
import rs.dodatnaoprema.dodatnaoprema.network.WebRequestCallbackInterface;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class NotificationManagerService extends IntentService {

    private VolleySingleton mVolleySingleton;

    public NotificationManagerService() {
        super("NotificationManagerService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null && intent.getExtras() != null) {
            for (String key : intent.getExtras().keySet()) {
                String value = intent.getExtras().getString(key);
                // let's see what's inside
                Log.e("NotificationManager", "Key: " + key + " Value: " + value);
            }
        }

        if (intent.getExtras() != null && intent.getExtras().containsKey("articleID")) {
            int articleID = Integer.valueOf(intent.getExtras().getString("articleID"));
            // now it would be cool to fire oneArticle view
            //mVolleySingleton = VolleySingleton.getsInstance(this);
            //viewArtcile(articleID);

            // inform about cart update
            Intent showArticle = new Intent(Config.SHOW_ARTICLE_DETAILS);
            showArticle.putExtra("articleID", articleID);
            LocalBroadcastManager.getInstance(this).sendBroadcast(showArticle);
        }
    }

//    public void viewArtcile(int itemID) {
//
//        PullWebContent<OneArticle> content =
//                new PullWebContent<>(OneArticle.class, UrlEndpoints.getRequestUrlArticleById(itemID), mVolleySingleton);
//
//
//        rs.dodatnaoprema.dodatnaoprema.common.utils.Log.logInfo("LALALA", String.valueOf(itemID));
//        content.setCallbackListener(new WebRequestCallbackInterface<OneArticle>() {
//            @Override
//            public void webRequestSuccess(boolean success, OneArticle oneArticle) {
//                if (success) {
//                    rs.dodatnaoprema.dodatnaoprema.common.utils.Log.logInfo("LALALA", "SUCCESS");
//                    Intent intent = new Intent(getApplicationContext(), OneArticleActivity.class);
//                    intent.putExtra(AppConfig.ABOUT_PRODUCT, oneArticle);
//                    startActivity(intent);
//
//                    rs.dodatnaoprema.dodatnaoprema.common.utils.Log.logInfo("LALALA", oneArticle.getArtikal().getArtikalNaziv());
//
//                } else {
//
//                    rs.dodatnaoprema.dodatnaoprema.common.utils.Log.logInfo("LALALA", "FAILED");
//                }
//            }
//
//            @Override
//            public void webRequestError(String error) {
//
//
//            }
//        });
//
//        content.pullList();
//    }
}
