package com.studio.suku.made.LocalDb;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Prefences {
     static final String daily = "daily";
     static final String release = "release";

    private static SharedPreferences getSharedPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setDaily(Context context, Boolean isChecked){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putBoolean(daily, isChecked);
        editor.apply();
    }

    public static void setRelease(Context context, Boolean isChecked){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putBoolean(release, isChecked);
        editor.apply();
    }

    public static Boolean getDaily(Context context){
        return getSharedPreference(context).getBoolean(daily,false);
    }

    public static Boolean getRelease(Context context){
        return getSharedPreference(context).getBoolean(release, false);
    }

}
