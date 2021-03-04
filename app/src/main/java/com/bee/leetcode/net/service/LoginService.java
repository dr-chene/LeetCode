package com.bee.leetcode.net.service;

import com.bee.leetcode.net.BeanDemo;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.rxjava3.core.Single;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

import static com.bee.leetcode.util.MapUtil.str2Json;

/**
 * created by dr_chene on 2021/2/5
 * desc 登录
 */
interface LoginService {

    @POST("/user/login")
    Single<BeanDemo> login(
            @Body RequestBody json
    );
}
