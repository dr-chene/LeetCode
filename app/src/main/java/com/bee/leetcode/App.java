package com.bee.leetcode;

import android.app.Application;

import com.bee.leetcode.db.AppDataBase;
import com.bee.leetcode.util.SpUtil;

public class App extends Application {
    //判断存储网络是否连接
    public static boolean connected = false;

    @Override
    public void onCreate() {
        super.onCreate();
        SpUtil.getInstance().initSp(getApplicationContext());
        AppDataBase.init(getApplicationContext());
    }
}
