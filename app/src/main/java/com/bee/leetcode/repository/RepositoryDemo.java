package com.bee.leetcode.repository;

import androidx.annotation.NonNull;

import com.bee.leetcode.db.DaoDemo;
import com.bee.leetcode.net.BeanDemo;
import com.bee.leetcode.net.service.LoginServiceProxy;
import com.bee.leetcode.util.SpUtil;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.rxjava3.core.Single;

/**
 * created by dr_chene on 2021/2/19
 * desc 网络框架示例repository部分
 */
public class RepositoryDemo {
    private final LoginServiceProxy api;
    private final DaoDemo local;

    public RepositoryDemo(DaoDemo local) {
        api = new LoginServiceProxy();
        this.local = local;
    }

    public void login(String account, String password, DataRequest.RequestSuccess<BeanDemo> success) {
        DataRequest.request(new DataRequest.RemoteOption<BeanDemo, BeanDemo>() {
            @NonNull
            @Override
            public Single<DataRequest.ApiResponse<BeanDemo>> get() {
                return api.login(account, password, "email");
            }

            @Override
            public BeanDemo remoteToLocal(BeanDemo t) {
                return t;
            }
        }, localOption, success);
    }

    public void load(DataRequest.RequestSuccess<BeanDemo> success) {
        DataRequest.request(remoteOption, localOption, success);
    }

    private final DataRequest.RemoteOption<BeanDemo, BeanDemo> remoteOption = new DataRequest.RemoteOption<BeanDemo, BeanDemo>() {
        @NonNull
        @Override
        public Single<DataRequest.ApiResponse<BeanDemo>> get() {
            return api.login("", "", "");
        }

        @Override
        public BeanDemo remoteToLocal(BeanDemo t) {
            return t;
        }
    };
    private final DataRequest.LocalOption<BeanDemo> localOption = new DataRequest.LocalOption<BeanDemo>() {
        @Override
        public Completable save(BeanDemo data) {
            SpUtil.getInstance().putString("user", data.toString());//一种存储方式
            return local.save(data);//另一种存储方式
        }

        @Override
        public io.reactivex.Single<List<BeanDemo>> get() {
            return local.get();
        }
    };
}
