package com.bee.leetcode.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bee.leetcode.db.bean.User;
import com.bee.leetcode.repository.DataRequest;
import com.bee.leetcode.repository.RepositoryDemo;

import java.util.List;

/**
 * created by dr_chene on 2021/2/19
 * desc 网络框架示例ViewModel部分
 */
public class ViewModelDemo extends ViewModel {
    private final RepositoryDemo repository;
    private final MutableLiveData<User> data;

    public ViewModelDemo(RepositoryDemo repository) {
        this.repository = repository;
        data = new MutableLiveData<>();
    }

    public LiveData<User> getData() {
        return data;
    }

    public void refresh(String account, String password) {
        repository.login(account, password, new DataRequest.RequestSuccess<User>() {
            @Override
            public void dispatchValue(User result) {
                data.postValue(result);
            }
        });
    }

    public void load(List<User> users) {
        repository.load(new DataRequest.RequestSuccess<User>() {
            @Override
            public void dispatchValue(User result) {
                //我一般习惯将recyclerview的list传进来，与请求到的list合并再post到livedata中
            }
        });
    }
}
