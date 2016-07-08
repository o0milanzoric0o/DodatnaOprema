package rs.dodatnaoprema.dodatnaoprema.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.common.application.MyApplication;
import rs.dodatnaoprema.dodatnaoprema.common.application.SessionManager;
import rs.dodatnaoprema.dodatnaoprema.fcm.MyPreferenceManager;
import rs.dodatnaoprema.dodatnaoprema.models.User;
import rs.dodatnaoprema.dodatnaoprema.models.articles.Article;
import rs.dodatnaoprema.dodatnaoprema.models.articles.brands.Brand;
import rs.dodatnaoprema.dodatnaoprema.models.articles.products_of_the_week.Product;
import rs.dodatnaoprema.dodatnaoprema.models.categories.all_categories.Category;
import rs.dodatnaoprema.dodatnaoprema.models.categories.you_may_also_like_categories.YMALCategory;

public class SharedPreferencesUtils {
    public static SharedPreferences.Editor getEditor(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        return prefs.edit();
    }

    public static void clearSharedPreferences(Context context, String key) {

        SharedPreferences.Editor editor = getEditor(context, key);
        editor.remove(key);
        editor.apply();
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<String> getArrayList(Context context, String key) {

        SharedPreferences prefs = context.getSharedPreferences(key, Context.MODE_PRIVATE);

        try {
            return (ArrayList<String>) ObjectSerializer.deserialize(prefs.getString(key, ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    public static void putArrayList(Context context, String key, ArrayList<String> mList) {

        SharedPreferences.Editor editor = getEditor(context, key);

        try {
            editor.putString(key, ObjectSerializer.serialize(mList));
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.apply();
    }

    public static void putArrayListArticle(Context context, String key, List<Article> mList) {

        SharedPreferences.Editor editor = getEditor(context, key);

        Gson gson = new Gson();
        String json = gson.toJson(mList);
        editor.putString(key, json);
        editor.commit();
    }

    public static List<Article> getArrayListArticle(Context context, String key) {

        SharedPreferences prefs = context.getSharedPreferences(key, Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String json = prefs.getString(key, "");
        Type type = new TypeToken<List<Article>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public static void putArrayListSpecificationIds(Context context, String key, List<List<Integer>> mList) {

        SharedPreferences.Editor editor = getEditor(context, key);

        Gson gson = new Gson();
        String json = gson.toJson(mList);
        editor.putString(key, json);
        editor.commit();
    }

    public static List<Article> getArrayListSpecificationIds(Context context, String key) {

        SharedPreferences prefs = context.getSharedPreferences(key, Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String json = prefs.getString(key, "");
        Type type = new TypeToken<List<List<Integer>>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public static void putArrayListYAML(Context context, String key, List<YMALCategory> mList) {

        SharedPreferences.Editor editor = getEditor(context, key);
        Gson gson = new Gson();
        String json = gson.toJson(mList);
        editor.putString(key, json);
        editor.commit();
    }

    public static List<YMALCategory> getArrayListYAML(Context context, String key) {

        SharedPreferences prefs = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(key, "");
        Type type = new TypeToken<List<YMALCategory>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public static void putArrayListProducts(Context context, String key, List<Product> mList) {

        SharedPreferences.Editor editor = getEditor(context, key);
        Gson gson = new Gson();
        String json = gson.toJson(mList);
        editor.putString(key, json);
        editor.commit();
    }

    public static List<Product> getArrayListProducts(Context context, String key) {

        SharedPreferences prefs = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(key, "");
        Type type = new TypeToken<List<Product>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public static void putArrayListBrands(Context context, String key, List<Brand> mList) {

        SharedPreferences.Editor editor = getEditor(context, key);
        Gson gson = new Gson();
        String json = gson.toJson(mList);
        editor.putString(key, json);
        editor.commit();
    }

    public static List<Brand> getArrayListBrands(Context context, String key) {

        SharedPreferences prefs = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(key, "");
        Type type = new TypeToken<List<Brand>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public static void putArrayListCategories(Context context, String key, List<Category> mList) {

        SharedPreferences.Editor editor = getEditor(context, key);
        Gson gson = new Gson();
        String json = gson.toJson(mList);
        editor.putString(key, json);
        editor.commit();
    }

    public static List<Category> getArrayListCategories(Context context, String key) {

        SharedPreferences prefs = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(key, "");
        Type type = new TypeToken<List<Category>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public static void putInt(Context context, String key, int value) {

        SharedPreferences.Editor editor = getEditor(context, key);
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getInt(Context context, String key) {

        SharedPreferences prefs = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        return prefs.getInt(key, 0);

    }

    public static void putString(Context context, String key, String value) {

        SharedPreferences.Editor editor = getEditor(context, key);
        editor.putString(key, value);
        editor.apply();
    }

    public static String getString(Context context, String key) {

        SharedPreferences prefs = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        return prefs.getString(key, "");

    }

    public static String getUserId() {
        MyPreferenceManager prefs = MyApplication.getInstance().getPrefManager();
        SessionManager session = MyApplication.getInstance().getSessionManager();
        //String userID = "";
        if (session.isLoggedIn()) {
            User user = prefs.getUser();
            if (user != null) {
                return user.getId();
            }
        }
        return "";

    }
    public static String getUserEmail() {
        MyPreferenceManager prefs = MyApplication.getInstance().getPrefManager();
        SessionManager session = MyApplication.getInstance().getSessionManager();
        //String userID = "";
        if (session.isLoggedIn()) {
            User user = prefs.getUser();
            if (user != null) {
                return user.getEmail();
            }
        }
        return "";

    }
}
