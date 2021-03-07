package com.bee.leetcode.net.service;

import com.bee.leetcode.net.BeanDemo;
import com.bee.leetcode.net.ServiceConstants;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.rxjava3.core.Single;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

import static com.bee.leetcode.util.MapUtil.str2Json;

/**
 * created by dr_chene on 2021/3/3
 * desc
 */
interface RegisterService {

    @POST("/user/register")
    Single<BeanDemo> register(
            @Body RequestBody json
    );
}
