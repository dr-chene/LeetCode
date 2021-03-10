package com.bee.leetcode.net.service;

import com.bee.leetcode.db.bean.ApiResponse;
import com.bee.leetcode.net.RetrofitFactory;
import com.bee.leetcode.net.ServiceConstants;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.rxjava3.core.Single;

import static com.bee.leetcode.util.MapUtil.str2Json;

/**
 * created by dr_chene on 2021/3/4
 * desc
 */
public class RegisterServiceProxy {

    private final RegisterService api;

    public RegisterServiceProxy() {
        api = RetrofitFactory.createWithNormal(RegisterService.class);
    }

    /**
     * @param method       {@link ServiceConstants}中method常量
     * @param registerBody method对应的phone或email账号
     */
    public Single<ApiResponse<String>> register(String registerBody, String password, int authCode, String method) {
        JSONObject json = new JSONObject();
        try {
            json.put("registerBody", registerBody);
            json.put("password", password);
            json.put("authCode", authCode);
            json.put("method", method);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return api.register(str2Json(json.toString()));
    }
}
