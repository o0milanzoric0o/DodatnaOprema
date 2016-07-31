package rs.dodatnaoprema.dodatnaoprema.common.application;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;

import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.fcm.MyPreferenceManager;
import rs.dodatnaoprema.dodatnaoprema.models.OfflineCart;
import rs.dodatnaoprema.dodatnaoprema.models.articles.Pictures;

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

    public int getCartItemCount() {
        if (isLoggedIn())
            return pref.getCartItemCount();
        else
            return getOfflineCartItemCount();
    }

    public void setCartItemCount(int count) {
        pref.setCartItemCount(count);
    }

    public int getOfflineCartItemCount() {
        return pref.getOfflineCartItemCount();
    }

    public void setOfflineCartItemCount(int count) {
        pref.setOfflineCartItemCount(count);
    }

    public void updateOfflineCart(int item_id, int quantity) {
        OfflineCart offlineCart = pref.loadOfflineCart();
        offlineCart.updateItemQuantity(item_id, quantity);
        setOfflineCartItemCount(offlineCart.getTotalQuantity());
        pref.saveOfflineCart(offlineCart);
    }

    public void addItemOfflineCart(int item_id, int quantity, String price, String title, List<Pictures> pics, String price_ext, Integer minQuantity) {
        OfflineCart offlineCart = pref.loadOfflineCart();
        offlineCart.addItem(item_id, quantity, price, title, pics, price_ext, minQuantity);
        Log.e("Korpa get_tot_quant:", String.valueOf(offlineCart.getTotalQuantity()));
        setOfflineCartItemCount(offlineCart.getTotalQuantity());
        pref.saveOfflineCart(offlineCart);
    }

    public JSONArray getCartItemsJsonArray(){
        OfflineCart offlineCart = pref.loadOfflineCart();
        return offlineCart.getItemsJSONArray();
    }
}