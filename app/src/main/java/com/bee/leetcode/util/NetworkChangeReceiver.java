package com.bee.leetcode.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.bee.leetcode.App;

/**
 * created by dr_chene on 2021/2/19
 * desc 监听网络连接状态
 */
public class NetworkChangeReceiver extends BroadcastReceiver {

    public static final String TAG = "NetworkChangeReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (isConnected && !App.connected) {
            Log.d(TAG, "onReceive: 网络可用");
            App.connected = true;
        } else if (!isConnected && App.connected) {
            ToastUtil.showToast(context, "网络不可用，请检查");
            Log.d(TAG, "onReceive: 网络不可用");
            App.connected = false;
        }
    }

}
