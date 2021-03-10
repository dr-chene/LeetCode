package com.bee.leetcode.net.service;

import com.bee.leetcode.db.bean.ApiResponse;
import com.bee.leetcode.db.bean.User;

import io.reactivex.rxjava3.core.Single;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * created by dr_chene on 2021/2/5
 * desc 登录
 */
interface LoginService {

    @POST("user/login")
    Single<ApiResponse<User>> login(
            @Body RequestBody json
    );
}
