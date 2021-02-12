package com.bee.leetcode.util;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

public class ToastUtil {
    private static Toast mToast;


    public static void showToast(Context mContext, String msg) {
        if (msg != null) {
            if (mToast != null) {
                //如果mToast对象已存在调用setText方法，避免mToast的重复创建
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
                //如果mToast对象已存在调用setText方法，避免mToast的重复创建
                mToast.setText(msg);
            } else {
                mToast = Toast.makeText(mContext, msg, duration);
            }
            mToast.show();
        }
    }
}
