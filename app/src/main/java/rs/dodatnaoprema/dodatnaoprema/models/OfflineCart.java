package rs.dodatnaoprema.dodatnaoprema.models;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import rs.dodatnaoprema.dodatnaoprema.models.articles.Pictures;

/**
 * ******************************
 * Created by milan on 7/20/2016.
 * ******************************
 */
public class OfflineCart implements Serializable {
    private ArrayList<OfflineCartItem> items;
    private String price_ext;  // DIN, EUR etc.

    public void addItem(int item_id, int quantity, String price, String title, List<Pictures> pics, String price_ext, Integer minQuantity) {
        if (items == null) {
            items = new ArrayList<>();
        }
        OfflineCartItem item = new OfflineCartItem();
        item.setItem_id(item_id);
        item.setQuantity(quantity);
        item.setPrice(price);
        item.setTotal_price(Float.valueOf(price) * quantity);
        item.setTitle(title);
        item.setPictures(pics);
        item.setPrice_ext(price_ext);
        item.setMinQuantity(minQuantity);
        this.price_ext = price_ext;
        items.add(item);
    }

    public void clearCart() {
        if (items != null) {
            items.clear();
        }
    }

    public void removeItem(int item_id) {
        if (items != null) {
            int index = -1;
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getItem_id() == item_id)
                    index = i;
            }
            if (index >= 0)
                items.remove(index);
        }
    }

    public void updateItemQuantity(int item_id, int quantity) {
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getItem_id() == item_id) {
                    items.get(i).setQuantity(quantity);
                    Float price = Float.valueOf(items.get(i).getPrice());
                    items.get(i).setTotal_price(price * quantity);
                }
            }
        }
    }

    public int getTotalQuantity() {
        int quantity = 0;
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                quantity += items.get(i).getQuantity();
            }
        }
        return quantity;
    }

    public String getTotalPrice(float shipping) {
        Float total = shipping;
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                total += items.get(i).getTotal_price();
            }
        }
        return String.format(Locale.US, "%.02f", total);
    }

    public ArrayList<OfflineCartItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<OfflineCartItem> items) {
        this.items = items;
    }

    public String getPrice_ext() {
        return price_ext;
    }

    public void setPrice_ext(String price_ext) {
        this.price_ext = price_ext;
    }

    public JSONArray getItemsJSONArray() {
        JSONArray jsonArray = new JSONArray();
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                jsonArray.put(items.get(i).getJSON());
            }
        }
        return jsonArray;
    }
}
