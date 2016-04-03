package com.tommeng.simplejackexample.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.TypedValue;

import com.tommeng.simplejackexample.R;

public class Utils {
    public static int getPrimaryTextColor(Context context) {
        TypedValue typedValue = new TypedValue();
        TypedArray a = context.obtainStyledAttributes(typedValue.data, new int[]{R.attr.colorPrimary});
        int color = a.getColor(0, 0);
        a.recycle();
        return color;
    }

    public static int getPrimaryTextColorDark(Context context) {
        TypedValue typedValue = new TypedValue();
        TypedArray a = context.obtainStyledAttributes(typedValue.data, new int[]{R.attr.colorPrimaryDark});
        int color = a.getColor(0, 0);
        a.recycle();
        return color;
    }

    //credit: http://stackoverflow.com/questions/24260853/check-if-color-is-dark-or-light-in-android
    public static boolean isColorDark(int color) {
        double darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255;
        return darkness >= 0.5;
    }
}
