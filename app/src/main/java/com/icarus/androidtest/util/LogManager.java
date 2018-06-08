package com.icarus.androidtest.util;

import android.util.Log;

public class LogManager {
    private static final String TAG = "LogManager";

    public static void v(String string) {
        Log.v(TAG, string);
    }

    public static void e(String string) {
        Log.e(TAG, string);
    }
}
