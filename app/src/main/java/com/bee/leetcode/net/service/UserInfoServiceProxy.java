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
public class UserInfoServiceProxy {

    private final UserInfoService api;

    public UserInfoServiceProxy() {
        api = RetrofitFactory.createWithNormal(UserInfoService.class);
    }

    /**
     * @param sex    1 boy, 0 girl
     * @param avatar 头像地址，通过上传头像接口获得
     */
    public Single<ApiResponse<String>> updateBaseInfo(String nickname, int sex, String avatar, String password) {
        JSONObject json = new JSONObject();
        try {
            json.put("nickname", nickname);
            json.put("password", password);
            json.put("sex", sex);
            json.put("avatar", avatar);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return api.updateBaseInfo(str2Json(json.toString()));
    }

    /**
     * @param method {@link ServiceConstants}中method常量
     */
    public Single<ApiResponse<String>> updateAccountInfo(String method, String code, String oldAccount, String newAccount) {
        JSONObject json = new JSONObject();
        try {
            json.put("newAccount", newAccount);
            json.put("oldAccount", oldAccount);
            json.put("code", code);
            json.put("method", method);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return api.updateAccountInfo(str2Json(json.toString()));
    }
}
