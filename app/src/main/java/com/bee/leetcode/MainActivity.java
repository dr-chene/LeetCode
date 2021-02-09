package com.bee.leetcode;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.bee.leetcode.base.BaseActivity;
import com.bee.leetcode.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding> {


    @Override
    public void initView(ActivityMainBinding binding) {
        LinearLayout mainLayout = binding.mainLayout;
    }

    @Override
    public void initAction(ActivityMainBinding binding) {

    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_main;
    }
}