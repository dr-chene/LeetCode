package com.bee.leetcode.repository;

import androidx.annotation.NonNull;

import com.bee.leetcode.db.DaoDemo;
import com.bee.leetcode.net.BeanDemo;
import com.bee.leetcode.net.service.ServiceDemo;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.rxjava3.core.Single;

/**
 * created by dr_chene on 2021/2/19
 * desc 网络框架示例repository部分
 */
public class RepositoryDemo extends NetworkBoundResource<BeanDemo, BeanDemo> {
    private final ServiceDemo api;
    private final DaoDemo local;

    public RepositoryDemo(ServiceDemo api, DaoDemo local) {
        this.api = api;
        this.local = local;
    }

    public boolean refresh(RequestSuccess<BeanDemo> success) {
        return request(success);
    }

    public boolean load(RequestSuccess<BeanDemo> success) {
        return request(success);
    }

    @Override
    protected Completable saveCallResult(BeanDemo item) {
        return local.save(item);
    }

    @Override
    protected boolean shouldFetch(BeanDemo data) {
        return false;
    }

    @Override
    protected Flowable<List<BeanDemo>> loadFromDb() {
        return local.get();
    }

    @NonNull
    @Override
    protected Single<ApiResponse<BeanDemo>> createCall() {
        return api.getRemoteData();
    }

    @Override
    protected boolean isApiRequestSuccess(ApiResponse<BeanDemo> beanDemoApiResponse) {
        return beanDemoApiResponse.code == 200;
    }

    @Override
    protected BeanDemo remoteToLocal(BeanDemo beanDemo) {
        return beanDemo;
    }

    @Override
    public void apiResultError(int error, String message) {

    }
}
