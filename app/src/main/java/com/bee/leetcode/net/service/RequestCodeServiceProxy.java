package com.bee.leetcode.net.service;

import com.bee.leetcode.db.bean.ApiResponse;
import com.bee.leetcode.net.RetrofitFactory;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.rxjava3.core.Single;

import static com.bee.leetcode.util.MapUtil.str2Json;

/**
 * created by dr_chene on 2021/3/4
 * desc 请求验证码代理类
 */
public class RequestCodeServiceProxy {

    private final RequestCodeService api;

    public RequestCodeServiceProxy() {
        api = RetrofitFactory.createWithNormal(RequestCodeService.class);
    }

    /**
     * @param method 获取验证码方式 {@link com.bee.leetcode.net.ServiceConstants}
     * @param number method对应的phone或者email账号
     */
    public Single<ApiResponse<String>> getCode(String method, String number) {
        JSONObject json = new JSONObject();
        try {
            json.put("method", method);
            json.put("number", number);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return api.getCode(str2Json(json.toString()));
    }
}
