package com.bee.leetcode.net.service;

import com.bee.leetcode.db.bean.ApiResponse;
import com.bee.leetcode.db.bean.User;
import com.bee.leetcode.net.RetrofitFactory;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.rxjava3.core.Single;

import static com.bee.leetcode.util.MapUtil.str2Json;

/**
 * created by dr_chene on 2021/3/4
 * desc
 */
public class LoginServiceProxy {

    private final LoginService api;

    public LoginServiceProxy() {
        api = RetrofitFactory.createWithNormal(LoginService.class);
    }

    /**
     * 参数与{@link RegisterService}含义一致
     */
    public Single<ApiResponse<User>> login(String registerBody, String password, String method) {
        JSONObject json = new JSONObject();
        try {
            json.put("registerBody", registerBody);
            json.put("password", password);
            json.put("method", method);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return api.login(str2Json(json.toString()));
    }
}
