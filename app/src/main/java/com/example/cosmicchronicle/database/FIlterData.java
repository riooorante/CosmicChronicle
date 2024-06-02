package com.example.cosmicchronicle.database;

import android.content.Context;
import android.content.SharedPreferences;

public class FIlterData {
    private static final String DB_NAME = "db_cosmic";

    public static void updateFilter(Context context, String filter){
        SharedPreferences.Editor editor = context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE).edit();
        editor.putString("filter", filter);
        editor.apply();
    }

    public static String getFilter(Context context){
        SharedPreferences data = context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        String filter = data.getString("filter", "terre");
        return filter;
    }

}
