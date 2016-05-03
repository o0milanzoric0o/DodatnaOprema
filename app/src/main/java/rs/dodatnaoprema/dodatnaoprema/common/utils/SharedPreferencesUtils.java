package rs.dodatnaoprema.dodatnaoprema.common.utils;


import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.util.ArrayList;

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

}
