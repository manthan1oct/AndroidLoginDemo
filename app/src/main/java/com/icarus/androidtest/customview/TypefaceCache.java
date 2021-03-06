
package com.icarus.androidtest.customview;

import android.content.res.AssetManager;
import android.graphics.Typeface;

import java.util.Hashtable;

public class TypefaceCache {

    private static final String FONT_LIGHT = "fonts/Roboto-Light.ttf";
    private static final String FONT_REGULAR = "fonts/Roboto-Regular.ttf";
    private static final String FONT_MEDIUM = "fonts/Roboto-Medium.ttf";
    private static final String FONT_BOLD = "fonts/Roboto-Bold.ttf";

    private static final Hashtable<String, Typeface> CACHE = new Hashtable<String, Typeface>();

    public static Typeface get(AssetManager manager, int typefaceCode) {
        synchronized (CACHE) {
            String typefaceName = getTypefaceName(typefaceCode);
            if (!CACHE.containsKey(typefaceName)) {
                Typeface t = Typeface.createFromAsset(manager, typefaceName);
                CACHE.put(typefaceName, t);
            }
            return CACHE.get(typefaceName);
        }
    }

    private static String getTypefaceName(int typefaceCode) {
        String typefaceTemp = "";
        switch (typefaceCode) {
            case 0:
                typefaceTemp = FONT_LIGHT;
                break;
            case 1:
                typefaceTemp = FONT_REGULAR;
                break;
            case 2:
                typefaceTemp = FONT_MEDIUM;
                break;
            case 3:
                typefaceTemp = FONT_BOLD;
                break;
            default:
                typefaceTemp = FONT_REGULAR;
        }
        return typefaceTemp;
    }
}
