package com.bee.leetcode.base;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class BaseActivity <T extends ViewDataBinding>extends AppCompatActivity implements IViewInit<T>{
    protected T binding;
    

    //获取TAG的activity名称
    protected final String TAG = this.getClass().getSimpleName();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        binding = DataBindingUtil.setContentView(this, getContentViewResId());

        initView(binding);
        initAction(binding);
        subscribe(binding);

    }





}
