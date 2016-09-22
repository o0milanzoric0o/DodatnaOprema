package rs.dodatnaoprema.dodatnaoprema.fcm;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import rs.dodatnaoprema.dodatnaoprema.SplashActivity;
import rs.dodatnaoprema.dodatnaoprema.common.application.MyApplication;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    private NotificationUtils notificationUtils;

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO(developer): Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
        //Log.e(TAG, "From: " + remoteMessage.getFrom());
        //Log.e(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
        MyPreferenceManager prefs = MyApplication.getInstance().getPrefManager();
        if(prefs.getNotificationsEnabled()) // If notifications are enabled, send it
            sendNotification(remoteMessage);

    }
    // [END receive_message]

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param remoteMessage FCM remote message received.
     */
    private void sendNotification(RemoteMessage remoteMessage) {
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
//                PendingIntent.FLAG_ONE_SHOT);
//
//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.drawable.ic_stat_ic_notification)
//                .setContentTitle("FCM Message")
//                .setContentText(messageBody)
//                .setAutoCancel(true)
//                .setSound(defaultSoundUri)
//                .setContentIntent(pendingIntent);
//
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
        //String title = remoteMessage.getNotification().getTitle();
        //String message = remoteMessage.getNotification().getBody();
        String title = remoteMessage.getData().get("title");
        String message = remoteMessage.getData().get("text");

        String imageURL = remoteMessage.getData().get("imageUrl");
        long timestamp = remoteMessage.getSentTime();
        String articleID = remoteMessage.getData().get("articleID");

        Intent intent = new Intent(this, SplashActivity.class);
        intent.putExtra("articleID", articleID);
        //intent.setAction("rs.masinealati.PROCESS_NOTIFICATION");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
//                PendingIntent.FLAG_ONE_SHOT);


        showNotificationMessage(getApplicationContext(), title, message, timestamp, intent);

//        if (TextUtils.isEmpty(imageURL)) {
//            showNotificationMessage(getApplicationContext(), title, message, timestamp, intent);
//        }else{
//            // Show notification with image
//            Log.e(TAG, "Notification with image...");
//            showNotificationMessageWithBigImage(getApplicationContext(), title, message, timestamp, intent, imageURL);
//        }

    }

    /**
     * Showing notification with text only
     */
    private void showNotificationMessage(Context context, String title, String message, long timeStamp, Intent intent) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent);
    }

    /**
     * Showing notification with text and image
     */
    private void showNotificationMessageWithBigImage(Context context, String title, String message, long timeStamp, Intent intent, String imageUrl) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent, imageUrl);
    }

}
