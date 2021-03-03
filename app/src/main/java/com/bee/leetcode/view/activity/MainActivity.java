package com.bee.leetcode.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.LinearLayout;


import com.bee.leetcode.DialogUtil;
import com.bee.leetcode.R;
import com.bee.leetcode.base.BaseActivity;
import com.bee.leetcode.databinding.ActivityMainBinding;


public class MainActivity extends BaseActivity<ActivityMainBinding> {


    @Override
    public void initView() {
    }

    @Override
    public void initAction() {

    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_main;
    }
}