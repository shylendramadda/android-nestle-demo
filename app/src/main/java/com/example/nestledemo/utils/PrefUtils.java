package com.example.nestledemo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.security.PublicKey;

public class PrefUtils {

    private static SharedPreferences mSharedPref;
    public static final String IS_LOGIN = "IS_LOGIN";
    public static final String MOBILE_NUM = "MOBILE_NUM";
    public static final String CARD_NUM = "CARD_NUM";
    public static final String CARD_NAME = "CARD_NAME";
    public static final String CARD_EXP = "CARD_EXP";
    public static final String CARD_CVV = "CARD_CVV";
    public static final String FIRST_NAME = "FIRST_NAME";
    public static final String LAST_NAME = "LAST_NAME";

    private PrefUtils() {
    }

    public static void init(Context context) {
        if (mSharedPref == null) {
            mSharedPref = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
        }
    }

    public static String read(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static void write(String key, String value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.apply();
    }

    public static int read(String key, int defValue) {
        return mSharedPref.getInt(key, defValue);
    }

    public static void write(String key, int value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putInt(key, value);
        prefsEditor.apply();
    }

    public static boolean read(String key, boolean defValue) {
        return mSharedPref.getBoolean(key, defValue);
    }

    public static void write(String key, boolean value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putBoolean(key, value);
        prefsEditor.apply();
    }

}
