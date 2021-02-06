package com.bee.leetcode.net.service;

import com.bee.leetcode.net.TempBean;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * created by dr_chene on 2021/2/5
 * desc 登录
 */
public interface LoginService {

    @POST("/user/login")
    Single<TempBean> login(
            @Body String username,
            @Body String password
    );
}
