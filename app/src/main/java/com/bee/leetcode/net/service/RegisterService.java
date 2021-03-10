package com.bee.leetcode.net.service;

import com.bee.leetcode.db.bean.ApiResponse;

import io.reactivex.rxjava3.core.Single;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * created by dr_chene on 2021/3/3
 * desc
 */
interface RegisterService {

    @POST("user/register")
    Single<ApiResponse<String>> register(
            @Body RequestBody json
    );
}
