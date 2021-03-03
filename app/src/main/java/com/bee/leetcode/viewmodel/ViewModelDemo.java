package com.bee.leetcode.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bee.leetcode.net.BeanDemo;
import com.bee.leetcode.repository.NetworkBoundResource;
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

    public boolean refresh() {
        return repository.refresh(new NetworkBoundResource.RequestSuccess<BeanDemo>() {
            @Override
            public void dispatchValue(BeanDemo beanDemo) {
                data.postValue(beanDemo);
            }
        });
    }

    public boolean load(BeanDemo bean) {
        return repository.load(new NetworkBoundResource.RequestSuccess<BeanDemo>() {
            @Override
            public void dispatchValue(BeanDemo beanDemo) {
                //我一般习惯将recyclerview的list传进来，与请求到的list合并再post到livedata中
            }
        });
    }
}
