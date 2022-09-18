package com.rezarinaldi.bukukasapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {
    static final String KEY_USERNAME ="USERNAME";
    static final String KEY_PASSWORD = "PASSWORD";
    static final String KEY_IS_FIRST_TIME = "IS_FIRST_TIME";

    private static SharedPreferences getSharedPreference(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setKeyUsername(Context context, String username) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USERNAME, username);
        editor.apply();
    }

    public static String getKeyUsername(Context context) {
        return getSharedPreference(context).getString(KEY_USERNAME,"");
    }

    public static void setKeyPassword(Context context, String password) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }

    public static String getKeyPassword(Context context) {
        return getSharedPreference(context).getString(KEY_PASSWORD,"");
    }

    public static void setKeyIsFirstTime(Context context, String isFirstTime) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_IS_FIRST_TIME, isFirstTime);
        editor.apply();
    }

    public static String getKeyIsFirstTime(Context context) {
        return getSharedPreference(context).getString(KEY_IS_FIRST_TIME,"");
    }
}
