package rs.dodatnaoprema.dodatnaoprema.common.application;

import android.content.Context;

import rs.dodatnaoprema.dodatnaoprema.gcm.MyPreferenceManager;

public class SessionManager {

    // Shared Preferences
    MyPreferenceManager pref;
    Context _context;

    public SessionManager(Context context) {
        this._context = context;
        pref = MyApplication.getInstance().getPrefManager();
    }

    public void setLogin(boolean isLoggedIn) {
        pref.setLogin(isLoggedIn);
    }

    public boolean isLoggedIn() {
        return pref.isLoggedIn();
    }
}