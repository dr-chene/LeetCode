package com.bee.leetcode.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    //获取TAG的activity名称
    protected final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置布局
        setContentView(initLayout());
        //初始化控件
        initView();
        //设置数据
        iniData();

    }
    //初始化布局 返回xml视图资源id
    protected abstract int initLayout();

    //初始化控件
    protected abstract int initView();

    //设置数据
    protected abstract void iniData();



}
