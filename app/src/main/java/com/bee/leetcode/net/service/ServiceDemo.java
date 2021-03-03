package com.bee.leetcode.net.service;

import com.bee.leetcode.net.BeanDemo;
import com.bee.leetcode.repository.NetworkBoundResource;

import io.reactivex.rxjava3.core.Single;

/**
 * created by dr_chene on 2021/3/3
 * desc
 */
public interface ServiceDemo {

    Single<NetworkBoundResource.ApiResponse<BeanDemo>> getRemoteData();
}
