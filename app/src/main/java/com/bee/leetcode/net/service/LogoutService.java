package com.bee.leetcode.net.service;

import com.bee.leetcode.net.TempBean;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.POST;

/**
 * created by dr_chene on 2021/2/6
 * desc 登出
 */
public interface LogoutService {

    @POST("/user/exit")
    Single<TempBean> logout();
}
