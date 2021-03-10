package com.bee.leetcode.net.service;

import com.bee.leetcode.db.bean.ApiResponse;
import com.bee.leetcode.net.RetrofitFactory;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.rxjava3.core.Single;

import static com.bee.leetcode.util.MapUtil.str2Json;

/**
 * created by dr_chene on 2021/3/4
 * desc
 */
public class ForgetServiceProxy {

    private final ForgetService api;

    public ForgetServiceProxy() {
        api = RetrofitFactory.createWithNormal(ForgetService.class);
    }

    /**
     * @param method,forGetBody 与{@link RegisterService}一致
     * @param code              发送的验证码，更改之前需要请求发送验证码
     */
    public Single<ApiResponse<String>> forget(String method, String forGetBody, String code, String newPassword) {
        JSONObject json = new JSONObject();
        try {
            json.put("forGetBody", forGetBody);
            json.put("newPassword", newPassword);
            json.put("code", code);
            json.put("method", method);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return api.forget(str2Json(json.toString()));
    }
}
