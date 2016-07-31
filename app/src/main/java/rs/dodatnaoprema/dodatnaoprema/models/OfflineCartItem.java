package rs.dodatnaoprema.dodatnaoprema.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.models.articles.Pictures;

/**
 * ******************************
 * Created by milan on 7/20/2016.
 * ******************************
 */
public class OfflineCartItem implements Serializable {
    private int item_id;
    private int quantity;
    private String price;
    private Float total_price;
    private String title;
    private List<Pictures> pictures;
    private String price_ext;  // DIN, EUR etc.
    private Integer minQuantity;

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Float total_price) {
        this.total_price = total_price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Pictures> getPictures() {
        return pictures;
    }

    public void setPictures(List<Pictures> pictures) {
        this.pictures = pictures;
    }

    public String getPrice_ext() {
        return price_ext;
    }

    public void setPrice_ext(String price_ext) {
        this.price_ext = price_ext;
    }

    public Integer getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
    }

    public JSONObject getJSON(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.accumulate("artikalID", item_id);
            jsonObject.accumulate("cena", total_price);
            jsonObject.accumulate("kolicina", quantity);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  jsonObject;
    }
}