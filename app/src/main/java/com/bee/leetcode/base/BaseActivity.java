package com.bee.leetcode.base;

import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.bee.leetcode.util.NetworkChangeReceiver;

public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity implements IViewInit {

    //获取TAG的activity名称
    protected final String TAG = this.getClass().getSimpleName();
    protected T binding;
    private NetworkChangeReceiver networkChangeReceiver;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        binding = DataBindingUtil.setContentView(this, getContentViewResId());
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));

        initView();
        initAction();
        subscribe();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(networkChangeReceiver);
        super.onDestroy();
    }
}
