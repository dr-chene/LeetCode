package com.bee.leetcode.net.service;

import com.bee.leetcode.net.TempBean;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * created by dr_chene on 2021/2/5
 * desc
 */
public interface ForgetService {

    @POST("/user/forget")
    Single<TempBean> forget(
            @Query("method") String method,
            @Query("username") String username,
            @Query("code") String code,
            @Query("newpassword") String newpassword
    );
}
