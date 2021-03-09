package com.bee.leetcode.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bee.leetcode.net.BeanDemo;
import com.bee.leetcode.repository.DataRequest;
import com.bee.leetcode.repository.RepositoryDemo;

/**
 * created by dr_chene on 2021/2/19
 * desc 网络框架示例ViewModel部分
 */
public class ViewModelDemo extends ViewModel {
    private final RepositoryDemo repository;
    private final MutableLiveData<BeanDemo> data;

    public ViewModelDemo(RepositoryDemo repository) {
        this.repository = repository;
        data = new MutableLiveData<>();
    }

    public void refresh(String account, String password) {
        repository.login(account, password, new DataRequest.RequestSuccess<BeanDemo>(){
            @Override
            public void dispatchValue(BeanDemo t) {
                data.postValue(t);
            }
        });
    }

    public void load(BeanDemo bean) {
        repository.load(new DataRequest.RequestSuccess<BeanDemo>() {
            @Override
            public void dispatchValue(BeanDemo beanDemo) {
                //我一般习惯将recyclerview的list传进来，与请求到的list合并再post到livedata中
            }
        });
    }
}
