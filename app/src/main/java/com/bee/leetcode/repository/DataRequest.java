package com.bee.leetcode.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * created by dr_chene on 2021/2/20
 * desc
 */
public class DataRequest {

    public static final String TAG = "NetworkBoundResource";

    private static io.reactivex.rxjava3.disposables.Disposable apiRequest;
    private static Disposable localRequest;
    private static Disposable insert;

    public static <Remote, Local> void request(@NonNull RemoteOption<Remote, Local> remoteOption, LocalOption<Local> localOption, RequestSuccess<Local> success){
        request(remoteOption, localOption, success, throwable ->{
            Log.e(TAG, "onRequestFailed: " + throwable.getMessage());
            throwable.printStackTrace();
        });
    }

    //请求数据，由外部调用：一般由viewmodel，配合livedata
    //了解流程即可，不必深挖细节
    //基本流程：如果有本地库，从本地库中加载数据，若没有可加载数据或者需要从远程同步数据，
    // 则进行网络请求，并将得到的数据插入本地数据库
    //否则直接进行网络请求
    /**
     *
     * @param remoteOption 远端操作，按需重写
     * @param localOption 本地操作，按需重写
     * @param success 数据请求成功回调
     * @param error rxjava使用过程发生错误回调
     * @param <Remote> 远程返回数据类型
     * @param <Local> 本地返回数据类型
     */
    public static <Remote, Local> void request(@NonNull RemoteOption<Remote, Local> remoteOption, LocalOption<Local> localOption, RequestSuccess<Local> success, RequestError error) {
//        Log.d(TAG, "request: more");
        io.reactivex.Single<List<Local>> local = localOption == null ? null : localOption.get();
        if (local == null) netRequest(remoteOption, localOption, success, error);
        else localRequest = local.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(locals -> {
//                    Log.d(TAG, "request: local");
                    if (locals.isEmpty() || localOption.shouldFetch(locals.get(0))) {
//                        Log.d(TAG, "request: remote");
                        netRequest(remoteOption, localOption, success, error);
                    } else {
//                        Log.d(TAG, "request: local success");
                        localOption.localRequestSuccess();
                        success.dispatchValue(locals.get(0));
                        if (localRequest != null && !localRequest.isDisposed()) {
                            localRequest.dispose();
                        }
                        localRequest = null;
                    }
                }, error::onError);
    }

    private static <Remote, Local> void netRequest(@NonNull RemoteOption<Remote, Local> remoteOption, LocalOption<Local> localOption, RequestSuccess<Local> success, RequestError error) {
        apiRequest = remoteOption.get().subscribeOn(io.reactivex.rxjava3.schedulers.Schedulers.io())
                .observeOn(io.reactivex.rxjava3.schedulers.Schedulers.io())
                .subscribe(remoteApiResponse -> {
                    if (remoteOption.isApiResponseSuccess(remoteApiResponse)) {
                        remoteOption.remoteRequestSuccess(remoteApiResponse.data);
                        Completable save = localOption == null ? null : localOption.save(remoteOption.remoteToLocal(remoteApiResponse.data));
                        if (save != null)
                            insert = save.subscribeOn(Schedulers.io())
                                    .subscribe(() -> {
                                        localOption.saveSuccess();
//                                        Log.d(TAG, "request: insert success");
                                        if (insert != null && !insert.isDisposed())
                                            insert.dispose();
                                        insert = null;
                                    }, error::onError);
                        success.dispatchValue(remoteOption.remoteToLocal(remoteApiResponse.data));
                    } else {
                        remoteOption.remoteResultError(remoteApiResponse.code, remoteApiResponse.message);
                    }
                    if (apiRequest != null && !apiRequest.isDisposed()) {
                        apiRequest.dispose();
                    }
                    apiRequest = null;
                }, error::onError);
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

    public interface RequestError{
        void onError(Throwable t);
    }

    //将网络操作与本地操作分离，按需重写
    public static abstract class RemoteOption<Remote, Local> {
        public abstract @NonNull Single<ApiResponse<Remote>> get();

        //远程数据转换为本地保存数据，一般remote与local类型相同，直接返回即可
        public abstract Local remoteToLocal(Remote t);

        //判断远程返回的数据是否正确
        public boolean isApiResponseSuccess(ApiResponse<Remote> response) {
            return response != null && response.code == 200;
        }

        //成功获取远程数据回调
        public void remoteRequestSuccess(Remote t) {
        }

        //网络获取数据异常回调
        public void remoteResultError(int code, String message) {
        }
    }

    public static class LocalOption<Local> {
        public io.reactivex.Single<List<Local>> get() {
            return null;
        }

        public Completable save(Local data) {
            return null;
        }

        //根据本地数据判断是否需要从远端获取数据
        public boolean shouldFetch(Local t) {
            return true;
        }

        //数据成功保存至本地回调
        public void saveSuccess() {
        }

        //成功获取本地数据回调，常做一些增加页数，判断是否还有数据等操作
        public void localRequestSuccess() {
        }
    }
}
