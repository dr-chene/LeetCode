package com.bee.leetcode.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {
    //获取TAG的fragment名称
    protected final String TAG = this.getClass().getSimpleName();
    public Context context;



    //初始化布局 返回布局xml视图的资源id
    protected abstract int initLayout();

    //初始化控件
    protected abstract void initView(final View view);


}
