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
import rs.dodatnaoprema.dodatnaoprema.gcm.MyPreferenceManager;
import rs.dodatnaoprema.dodatnaoprema.models.User;
import rs.dodatnaoprema.dodatnaoprema.models.articles.Article;

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

    public static ArrayList<String> getArrayList(Context context, String key) {

        SharedPreferences prefs = context.getSharedPreferences(key, Context.MODE_PRIVATE);

        try {
            return (ArrayList<String>) ObjectSerializer.deserialize(prefs.getString(key, ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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
        Type type = new TypeToken<List<Article>>(){}.getType();
        List<Article> students= gson.fromJson(json, type);

        return students;
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
}
