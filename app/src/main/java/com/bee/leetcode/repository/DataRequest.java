package com.bee.leetcode.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.bee.leetcode.db.bean.ApiResponse;

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

    public static <Remote, Local> void request(@NonNull Single<ApiResponse<Remote>> remote, @NonNull Transfer<Remote, Local> transfer, RequestSuccess<Local> success) {
        request(remote, transfer, null, success);
    }

    public static <Remote, Local> void request(@NonNull Single<ApiResponse<Remote>> remote, @NonNull Transfer<Remote, Local> transfer, LocalOption<Local> localOption, RequestSuccess<Local> success) {
        request(new RemoteOption<Remote, Local>().setRemoteGet(remote).setTransfer(transfer), localOption, success);
    }

    //推荐调用此接口
    public static <Remote, Local> void request(@NonNull RemoteOption<Remote, Local> remoteOption, RequestSuccess<Local> success) {
        request(remoteOption, null, success);
    }

    //推荐调用此接口
    public static <Remote, Local> void request(@NonNull RemoteOption<Remote, Local> remoteOption, LocalOption<Local> localOption, RequestSuccess<Local> success) {
        request(remoteOption, localOption, false, success);
    }

    //推荐调用此接口
    public static <Remote, Local> void request(@NonNull RemoteOption<Remote, Local> remoteOption, LocalOption<Local> localOption, boolean twice, RequestSuccess<Local> success) {
        request(remoteOption, localOption, twice, success, throwable -> {
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
     * @param remoteOption 远端操作，按需重写
     * @param localOption  本地操作，按需重写
     * @param success      数据请求成功回调
     * @param error        rxjava使用过程发生错误回调
     * @param <Remote>     远程返回数据类型
     * @param <Local>      本地返回数据类型
     * @param twice        获取到本地数据后是否需要再向远程请求数据
     */
    public static <Remote, Local> void request(@NonNull RemoteOption<Remote, Local> remoteOption, LocalOption<Local> localOption, boolean twice, RequestSuccess<Local> success, RequestError error) {
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
                        if (twice) netRequest(remoteOption, localOption, success, error);
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
                        boolean otherWaySave = localOption != null && localOption.saveByOtherWay(remoteOption.remoteToLocal(remoteApiResponse.data));
                        if (otherWaySave) localOption.saveSuccess();
                        success.dispatchValue(remoteOption.remoteToLocal(remoteApiResponse.data));
                    } else {
                        remoteOption.remoteResultFailed(remoteApiResponse.code, remoteApiResponse.message);
                    }
                    if (apiRequest != null && !apiRequest.isDisposed()) {
                        apiRequest.dispose();
                    }
                    apiRequest = null;
                }, error::onError);
    }

    public interface RequestSuccess<Local> {
        void dispatchValue(Local result);
    }

    public interface RequestError {
        void onError(Throwable t);
    }

    //将网络操作与本地操作分离，按需设置回调
    public static final class RemoteOption<Remote, Local> {

        private Single<ApiResponse<Remote>> remoteGet = null;
        private Transfer<Remote, Local> transfer = null;
        private Fun1<ApiResponse<Remote>> isResponseSuccess = null;
        private Fun1<Remote> remoteRequestSuccessListener = null;
        private Fun2<Integer, String> remoteResultFailedListener = null;

        public RemoteOption<Remote, Local> setRemoteGet(Single<ApiResponse<Remote>> remoteGet) {
            this.remoteGet = remoteGet;
            return this;
        }

        public RemoteOption<Remote, Local> setTransfer(Transfer<Remote, Local> transfer) {
            this.transfer = transfer;
            return this;
        }

        public RemoteOption<Remote, Local> setIsResponseSuccess(Fun1<ApiResponse<Remote>> isResponseSuccess) {
            this.isResponseSuccess = isResponseSuccess;
            return this;
        }

        public RemoteOption<Remote, Local> setRemoteRequestSuccessListener(Fun1<Remote> remoteRequestSuccess) {
            this.remoteRequestSuccessListener = remoteRequestSuccess;
            return this;
        }

        public RemoteOption<Remote, Local> setRemoteResultFailedListener(Fun2<Integer, String> remoteResultFailed) {
            this.remoteResultFailedListener = remoteResultFailed;
            return this;
        }

        @NonNull
        private Single<ApiResponse<Remote>> get() {
            if (remoteGet == null) throw new NullPointerException("请求远端数据的接口不能为空，调用setRemote设置.");
            return remoteGet;
        }

        //远程数据转换为本地保存数据，一般remote与local类型相同，直接返回即可
        @NonNull
        private Local remoteToLocal(Remote t) {
            if (transfer == null) throw new NullPointerException("远端数据转换的接口不能为空，调用setTans设置.");
            return transfer.trans(t);
        }

        //判断远程返回的数据是否正确
        private boolean isApiResponseSuccess(ApiResponse<Remote> response) {
            if (isResponseSuccess != null) return isResponseSuccess.run(response);
            else return response != null && response.code == 200;
        }

        //成功获取远程数据回调
        private void remoteRequestSuccess(Remote t) {
            if (remoteRequestSuccessListener != null) remoteRequestSuccessListener.run(t);
        }

        //网络获取数据失败回调
        private void remoteResultFailed(int code, String message) {
            if (remoteResultFailedListener != null) remoteResultFailedListener.run(code, message);
        }

    }

    public static final class LocalOption<Local> {

        private io.reactivex.Single<List<Local>> localGet = null;
        private Fun0 saveSuccessListener = null;
        private Fun0 localRequestSuccessListener = null;
        private Fun1<Local> shouldFetch = null;
        private Fun1<Local> otherWayToSave = null;
        private Save<Local> localSave = null;

        public LocalOption<Local> setLocalGet(io.reactivex.Single<List<Local>> localGet) {
            this.localGet = localGet;
            return this;
        }

        public LocalOption<Local> setLocalSave(Save<Local> localSave) {
            this.localSave = localSave;
            return this;
        }

        public LocalOption<Local> setSaveSuccessListener(Fun0 saveSuccess) {
            this.saveSuccessListener = saveSuccess;
            return this;
        }

        public LocalOption<Local> setLocalRequestSuccessListener(Fun0 localRequestSuccess) {
            this.localRequestSuccessListener = localRequestSuccess;
            return this;
        }

        public LocalOption<Local> setShouldFetch(Fun1<Local> shouldFetch) {
            this.shouldFetch = shouldFetch;
            return this;
        }

        //设置其他存储方式
        public LocalOption<Local> setOtherWaySaveListener(Fun1<Local> otherSave) {
            this.otherWayToSave = otherSave;
            return this;
        }

        private io.reactivex.Single<List<Local>> get() {
            return localGet;
        }

        private Completable save(Local data) {
            return localSave == null ? null : localSave.save(data);
        }

        private boolean saveByOtherWay(Local data) {
            return otherWayToSave == null || otherWayToSave.run(data);
        }

        //根据本地数据判断是否需要从远端获取数据，默认不需要
        private boolean shouldFetch(Local t) {
            if (shouldFetch == null) return false;
            else return shouldFetch.run(t);
        }

        //数据成功保存至本地回调
        private void saveSuccess() {
            if (saveSuccessListener != null) {
                saveSuccessListener.run();
            }
        }

        //成功获取本地数据回调，常做一些增加页数，判断是否还有数据等操作
        private void localRequestSuccess() {
            if (localRequestSuccessListener != null) {
                localRequestSuccessListener.run();
            }
        }
    }

    //数据转换接口
    public interface Transfer<Remote, Local> {
        Local trans(Remote r);
    }

    public interface Fun0 {
        void run();
    }

    public interface Fun1<T> {
        boolean run(T t);
    }

    public interface Save<T> {
        Completable save(T data);
    }

    public interface Fun2<T, V> {
        void run(T t, V v);
    }
}