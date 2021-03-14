package com.bee.leetcode.util;

import android.util.Log;

import androidx.databinding.library.baseAdapters.BuildConfig;


public class LogUtil {
    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;

    //总开关控制打印等级
    public static final int LEVEL = VERBOSE;
    //控制在debug模式下打印，release模式下不打印
    public static final boolean IS_DEBUG = BuildConfig.DEBUG;
    //默认TAG
    public static final String TAG = "Bee";

    //只有msg参数
    public static void log(String msg) {
        System.out.println(IS_DEBUG);
        if (IS_DEBUG && LEVEL <= DEBUG) {
            Log.d(TAG, msg);
        }
    }

    //输入tag,msg两个参数
    public static void log(String tag, String msg) {
        if (IS_DEBUG && LEVEL <= DEBUG) {
            Log.d(tag, msg);
        }
    }

    //输入tag,msg,level三个参数,若传入的level不合法，则默认为debug
    public static void log(String tag, String msg, int type) {
        switch (type) {
            case VERBOSE:
                if (IS_DEBUG && LEVEL <= VERBOSE) {
                    Log.v(tag, msg);
                }
                break;
            case DEBUG:
                if (IS_DEBUG && LEVEL <= DEBUG) {
                    Log.d(tag, msg);
                }
                break;
            case INFO:
                if (IS_DEBUG && LEVEL <= INFO) {
                    Log.i(tag, msg);
                }
                break;
            case WARN:
                if (IS_DEBUG && LEVEL <= WARN) {
                    Log.w(tag, msg);
                }
                break;
            case ERROR:
                if (IS_DEBUG && LEVEL <= ERROR) {
                    Log.e(tag, msg);
                }
                break;
            default:
                if (IS_DEBUG && LEVEL <= ERROR) {
                    Log.d(tag, msg);
                }
                break;
        }
    }
}
