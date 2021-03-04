package com.bee.leetcode.repository;

import android.util.Log;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * created by dr_chene on 2021/2/20
 * desc 数据请求框架
 * Remote：远程返回的数据类型
 * Local：本地存储的数据类型
 * 一般remote与local相同，不过难免有例外
 * 由repository继承，实现相应方法即可
 */
public abstract class NetworkBoundResource<Remote, Local> {

    public static final String TAG = "NetworkBoundResource";

    private io.reactivex.rxjava3.disposables.Disposable apiRequest;
    private Disposable localRequest;
    private Disposable insert;

    //保存远程获取的数据到本地，无则返回null
    @WorkerThread
    protected abstract Completable saveCallResult(Local item);

    //判断是否需要从远程获取数据
    @MainThread
    protected abstract boolean shouldFetch(Local data);

    //从本地获取数据，无则返回null
    @MainThread
    protected abstract Flowable<List<Local>> loadFromDb();

    //发起请求，获取远程数据
    @MainThread
    protected abstract @NonNull io.reactivex.rxjava3.core.Single<ApiResponse<Remote>> createCall();

    //rxjava使用过程中发生的错误、异常
    protected void onFetchFailed(Throwable t) {
        Log.e(TAG, "onFetchFailed: " + t.getMessage());
    }

    //判断远程返回的数据是否正常
    protected abstract boolean isApiRequestSuccess(ApiResponse<Remote> remoteApiResponse);

    //远程数据转换为本地保存数据
    protected abstract Local remoteToLocal(Remote remote);

    //数据成功保存至本地
    protected void insertSuccess() {
    }

    //远程返回数据异常
    public abstract void apiResultError(int error, String message);

    //成功获取本地数据，常做一些增加页数，判断是否还有数据等操作
    protected void localRequestSuccess(Local data) {
    }

    // 成功获取远程数据，如上 localRequestSuccess
    protected void apiRequestSuccess(Remote data) {
    }

    //在请求数据前调用，用于判定是否还有数据
    protected boolean isMore() {
        return true;
    }

    //用于取消flowable的订阅
    //flowable在观察到数据变化之后，取消订阅，以防止在刷新及加载数据是产生冲突
    private synchronized void unSubscribe(Disposable d) {
        if (d != null && !d.isDisposed()) {
            d.dispose();
        }
    }

    //请求数据，由外部调用：一般由viewmodel，配合livedata
    //有更多数据是返回true，否则false
    //使用时千万注意调用的条件，否则就会陷入local，remote的无限循环调用
    //了解流程即可，不必深挖细节
    public final boolean request(RequestSuccess<Local> success) {
        if (!isMore()) return false;
//        Log.d(TAG, "request: more");
        Flowable<List<Local>> local = loadFromDb();
        if (local == null) netRequest(success);
        else localRequest = local.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(locals -> {
//                    Log.d(TAG, "request: local");
                    if (locals.isEmpty() || shouldFetch(locals.get(0))) {
//                        Log.d(TAG, "request: remote");
                        netRequest(success);
                    } else {
//                        Log.d(TAG, "request: local success");
                        localRequestSuccess(locals.get(0));
                        success.dispatchValue(locals.get(0));
                        unSubscribe(localRequest);
                        localRequest = null;
                    }
                }, this::onFetchFailed);
        return true;
    }

    private void netRequest(RequestSuccess<Local> success) {
        apiRequest = createCall().subscribeOn(io.reactivex.rxjava3.schedulers.Schedulers.io())
                .observeOn(io.reactivex.rxjava3.schedulers.Schedulers.io())
                .subscribe(remoteApiResponse -> {
                    if (isApiRequestSuccess(remoteApiResponse)) {
                        apiRequestSuccess(remoteApiResponse.data);
                        Completable save = saveCallResult(remoteToLocal(remoteApiResponse.data));
                        if (save != null)
                            insert = save.subscribeOn(Schedulers.io())
                                    .subscribe(() -> {
                                        insertSuccess();
//                                        Log.d(TAG, "request: insert success");
                                        unSubscribe(insert);
                                        insert = null;
                                    }, this::onFetchFailed);
                        else success.dispatchValue(remoteToLocal(remoteApiResponse.data));
                    } else {
                        apiResultError(remoteApiResponse.code, remoteApiResponse.message);
                    }
                    if (apiRequest != null) {
                        apiRequest.dispose();
                        apiRequest = null;
                    }
                }, this::onFetchFailed);
    }

    //返回的网络数据格式
    public static class ApiResponse<T> {
        public T data;
        public int code;
        public String message;
    }

    public interface RequestSuccess<Local> {
        void dispatchValue(Local data);
    }
}
