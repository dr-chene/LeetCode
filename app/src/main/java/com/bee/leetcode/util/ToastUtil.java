package com.bee.leetcode.util;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

public class ToastUtil {
    private static Toast mToast;

    //调用时无需传入显示时间，默认为LENGTH_SHORT
    public static void showToast(Context mContext, String msg) {
        if (msg != null) {
            //如果mToast已经存在，则直接调用setText方法，避免重复显示
            if (mToast != null) {
                mToast.setText(msg.trim());
            } else {
                mToast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
            }
            mToast.show();
        }
    }

    public static void showToast(Context mContext, String msg, int duration) {
        if (msg != null) {
            if (mToast != null) {
                mToast.setText(msg);
            } else {
                mToast = Toast.makeText(mContext, msg, duration);
            }
            mToast.show();
        }
    }

    //删除正在显示的Toast
    public static void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
