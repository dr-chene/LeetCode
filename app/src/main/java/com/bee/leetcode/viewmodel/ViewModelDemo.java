package com.bee.leetcode.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bee.leetcode.net.BeanDemo;
import com.bee.leetcode.repository.RepositoryDemo;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.FlowableSubscriber;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

/**
 * created by dr_chene on 2021/2/19
 * desc
 */
public class ViewModelDemo extends ViewModel {
    private final RepositoryDemo repository;
    private final MutableLiveData<List<BeanDemo>> data;

    public ViewModelDemo(RepositoryDemo repository) {
        this.repository = repository;
        data = new MutableLiveData<>();
        repository.get().subscribe(new FlowableSubscriber<BeanDemo>() {
            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Subscription s) {

            }

            @Override
            public void onNext(BeanDemo beanDemo) {
                List<BeanDemo> list = new ArrayList<>();
                list.add(beanDemo);
                data.postValue(list);
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public LiveData<List<BeanDemo>> getData() {
        return data;
    }

    public void refresh(){
        repository.refresh(new DisposableSingleObserver<BeanDemo>() {
            @Override
            public void onSuccess(@NonNull BeanDemo beanDemo) {
                repository.save(beanDemo);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.printStackTrace();
            }
        });
    }
}
