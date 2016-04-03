package com.tommeng.simplejackexample.util;

import android.graphics.Color;

public class Utils {
    //credit: http://stackoverflow.com/questions/24260853/check-if-color-is-dark-or-light-in-android
    public static boolean isColorDark(int color) {
        double darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255;
        return darkness >= 0.5;
    }
}
