package rs.dodatnaoprema.dodatnaoprema.gcm;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;

import rs.dodatnaoprema.dodatnaoprema.models.User;

/**
 * Created by 1 on 4/19/2016.
 */
public class MyPreferenceManager {

    // Sharedpref file name
    private static final String PREF_NAME = "androidhive_gcm";
    // All Shared Preferences Keys
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_PHOTO = "user_photo";

    private static final String KEY_NOTIFICATIONS = "notifications";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    private static final String KEY_CART_ITEM_COUNT = "CartItemCount";

    private static final String KEY_GENERAL_NAME = "KomitentNaziv";
    private static final String KEY_NAME = "KomitentIme";
    private static final String KEY_LAST_NAME = "KomitentPrezime";
    private static final String KEY_ADDRESS = "KomitentAdresa";
    private static final String KEY_ZIP_CODE = "KomitentPosBroj";
    private static final String KEY_CITY = "KomitentMesto";
    private static final String KEY_PHONE = "KomitentTelefon";
    private static final String KEY_MOBILE = "KomitentMobTel";
    private static final String KEY_USER_EMAIL = "KomitentEmail";
    private static final String KEY_USER_NAME = "KomitentUserName";
    private static final String KEY_USER_TYPE = "KomitentTipUsera";
    private static final String KEY_FIRM_NAME = "KomitentFirma";
    private static final String KEY_FIRM_ID = "KomitentMatBr";
    private static final String KEY_FIRM_PIB = "KomitentPIB";
    private static final String KEY_FIRM_ADDRESS = "KomitentFirmaAdresa";
    // Shared Preferences
    SharedPreferences pref;
    // Editor for Shared preferences
    SharedPreferences.Editor editor;
    // Context
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;
    private String TAG = MyPreferenceManager.class.getSimpleName();

    // Constructor
    public MyPreferenceManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void storeUser(User user) {
        if (user.getId() != null)
            editor.putString(KEY_USER_ID, user.getId());
        if (user.getName() != null)
            editor.putString(KEY_NAME, user.getName());
        if (user.getEmail() != null)
            editor.putString(KEY_USER_EMAIL, user.getEmail());
        if (user.getPhoto() != null)
            editor.putString(KEY_USER_PHOTO, user.getPhoto().toString());
        if (user.getGeneral_name() != null)
            editor.putString(KEY_GENERAL_NAME, user.getGeneral_name());
        if (user.getLast_name() != null)
            editor.putString(KEY_LAST_NAME, user.getLast_name());
        if (user.getAddress() != null)
            editor.putString(KEY_ADDRESS, user.getAddress());
        if (user.getZip_code() != null)
            editor.putString(KEY_ZIP_CODE, user.getZip_code());
        if (user.getCity() != null)
            editor.putString(KEY_CITY, user.getCity());
        if (user.getPhone() != null)
            editor.putString(KEY_PHONE, user.getPhone());
        if (user.getMobile() != null)
            editor.putString(KEY_MOBILE, user.getMobile());
        if (user.getUserName() != null)
            editor.putString(KEY_USER_NAME, user.getUserName());
        if (user.getUserType() != null)
            editor.putString(KEY_USER_TYPE, user.getUserType());
        if (user.getUserFirmName() != null)
            editor.putString(KEY_FIRM_NAME, user.getUserFirmName());
        if (user.getFirmID() != null)
            editor.putString(KEY_FIRM_ID, user.getFirmID());
        if (user.getFirmPIB() != null)
            editor.putString(KEY_FIRM_PIB, user.getFirmPIB());
        if (user.getFirmAddress() != null)
            editor.putString(KEY_FIRM_ADDRESS, user.getFirmAddress());
        editor.commit();

        Log.e(TAG, "User is stored in shared preferences. " + user.getName() + ", " + user.getEmail());
    }

    public User getUser() {
        if (pref.getString(KEY_USER_ID, null) != null) {
            String id, name, email;
            id = pref.getString(KEY_USER_ID, null);
            name = pref.getString(KEY_NAME, null);
            email = pref.getString(KEY_USER_EMAIL, null);

            User user = new User(id, name, email);

            if (pref.getString(KEY_USER_PHOTO, null) != null)
                user.setPhoto(Uri.parse(pref.getString(KEY_USER_PHOTO, null)));
            user.setGeneral_name(pref.getString(KEY_GENERAL_NAME, null));
            user.setLast_name(pref.getString(KEY_LAST_NAME, null));
            user.setAddress(pref.getString(KEY_ADDRESS, null));
            user.setZip_code(pref.getString(KEY_ZIP_CODE, null));
            user.setCity(pref.getString(KEY_CITY, null));
            user.setPhone(pref.getString(KEY_PHONE, null));
            user.setMobile(pref.getString(KEY_MOBILE, null));
            user.setUserName(pref.getString(KEY_USER_NAME, null));
            user.setUserType(pref.getString(KEY_USER_TYPE, null));
            user.setUserFirmName(pref.getString(KEY_FIRM_NAME, null));
            user.setFirmID(pref.getString(KEY_FIRM_ID, null));
            user.setFirmPIB(pref.getString(KEY_FIRM_PIB, null));
            user.setFirmAddress(pref.getString(KEY_FIRM_ADDRESS, null));

            return user;
        }
        return null;
    }

    public void addNotification(String notification) {

        // get old notifications
        String oldNotifications = getNotifications();

        if (oldNotifications != null) {
            oldNotifications += "|" + notification;
        } else {
            oldNotifications = notification;
        }

        editor.putString(KEY_NOTIFICATIONS, oldNotifications);
        editor.commit();
    }

    public String getNotifications() {
        return pref.getString(KEY_NOTIFICATIONS, null);
    }

    public void clear() {
        editor.clear();
        editor.commit();
    }


    public void setLogin(boolean isLoggedIn) {

        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        // commit changes
        editor.commit();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public int getCartItemCount() {
        return pref.getInt(KEY_CART_ITEM_COUNT, 0);
    }

    public void setCartItemCount(int count) {
        editor.putInt(KEY_CART_ITEM_COUNT, count);
        editor.commit();
    }

}