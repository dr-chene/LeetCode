package com.bee.leetcode.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtil {
    /**
     * 保存在手机里面的文件名
     */
    public static final String FILE_NAME = "bee_leetcode_data";

    /**
     * 单例对象
     */
    private volatile static SpUtil mInstance;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private SpUtil() {

    }

    //单例模式
    public static SpUtil getInstance() {
        if (mInstance == null) {
            synchronized (SpUtil.class) {
                if (mInstance == null) {
                    mInstance = new SpUtil();
                }
            }
        }
        return mInstance;
    }

    //初始化SharedPreferences对象和Editor对象
    public void initSp(Context mContext) {
        sp = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    //存储int类型的值
    public void putInt(String key, int values) {
        editor.putInt(key, values);
        editor.commit();
    }

    //根据key来读取int类型的值，传入的第二个参数为默认返回值
    public int getInt(String key, int defValues) {
        return sp.getInt(key, defValues);
    }

    //存储String类型的值
    public void putString(String key, String values) {
        editor.putString(key, values);
        editor.commit();
    }

    //根据key来读取String类型的值，传入的第二个参数为默认返回值
    public String getString(String key, String defValues) {
        return sp.getString(key, defValues);
    }

    //存储Boolean类型的值
    public void putBoolean(String key, boolean values) {
        editor.putBoolean(key, values);
        editor.commit();
    }

    //根据key来读取boolean类型的值，传入的第二个参数为默认返回值
    public boolean getBoolean(String key, boolean values) {
        return sp.getBoolean(key, values);
    }

    //清除指定数据（根据key）
    public void deleteKey(String key) {
        editor.remove(key);
        editor.commit();
    }

    //清空所有数据
    public void deleteAll() {
        editor.clear();
        editor.commit();
    }
}
