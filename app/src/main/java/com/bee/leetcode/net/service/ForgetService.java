package com.bee.leetcode.net.service;

import com.bee.leetcode.db.bean.ApiResponse;

import io.reactivex.rxjava3.core.Single;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * created by dr_chene on 2021/2/5
 * desc 忘记密码，有两种方式：phone，email
 */
public interface ForgetService {

    @POST("user/forget")
    Single<ApiResponse<String>> forget(
            @Body RequestBody json
    );
}
