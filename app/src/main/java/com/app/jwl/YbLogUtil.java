package com.app.jwl;

import android.util.Log;

import static com.app.jwl.BuildConfig.LOG_DEBUG;

/**
 * Author：YangBin
 * Time：2017/2/21.
 * Email：1250211588@qq.com
 * explain：
 */

public class YbLogUtil {
    public static void d(String s) {
        if (LOG_DEBUG)
            Log.d("yangbin", s);
    }

    public static void d(Object obj, String s) {
        if (LOG_DEBUG)
            Log.d("yangbin", obj.getClass().getName() + "\n" + s);
    }
}
