package com.bee.leetcode.net.service;

import com.bee.leetcode.net.BeanDemo;

import io.reactivex.rxjava3.core.Single;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

import static com.bee.leetcode.util.MapUtil.str2Form;

/**
 * created by dr_chene on 2021/2/5
 * desc 忘记密码，有两种方式：phone，email
 */
public interface ForgetService {

    @POST("/user/forget")
    Single<BeanDemo> forget(
            @Body RequestBody json
    );
}
