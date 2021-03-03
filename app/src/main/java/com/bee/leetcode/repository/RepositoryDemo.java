package com.bee.leetcode.repository;

import com.bee.leetcode.db.DaoDemo;
import com.bee.leetcode.net.BeanDemo;
import com.bee.leetcode.net.service.ActiveService;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

/**
 * created by dr_chene on 2021/2/19
 * desc
 */
public class RepositoryDemo {
    private ActiveService api;
    private DaoDemo local;

    public RepositoryDemo(ActiveService api, DaoDemo local) {
        this.api = api;
        this.local = local;
    }

    public void refresh(DisposableSingleObserver<BeanDemo> observer){
        api.getActivity().safeSubscribe(observer);
    }

    public Flowable<BeanDemo> get(){
        return local.get();
    }

    public Completable save(BeanDemo t){
        return local.save(t);
    }
}
