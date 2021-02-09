package com.bee.leetcode;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bee.leetcode.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected int initView() {
        return 0;
    }


}