package com.bee.leetcode.util;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.FlowableTransformer;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * created by dr_chene on 2021/2/4
 * desc rxjava的封装
 */
public class RxjavaUtil {


    /**
     * 对rxjava flowable的封装
     * @param flow 监听的flowable对象，从网络和本地获取
     * @param subscriber 对flow发射的结果的订阅器，在此处处理返回的数据结果
     * @param <T> 数据bean类
     */
    public static <T> void dataRequest(Flowable<T> flow, FlowableSubscriber<T> subscriber){
        flow.compose(flowableToMain())
                .subscribe(subscriber);
    }

    //切换线程
    private static <T> FlowableTransformer<T, T> flowableToMain(){
        return new FlowableTransformer<T, T>() {
            @Override
            public @NonNull Publisher<T> apply(@NonNull Flowable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
