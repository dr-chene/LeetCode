package com.bee.leetcode.net.service;

import com.bee.leetcode.db.bean.User;
import com.bee.leetcode.net.ServiceConstants;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.rxjava3.core.Single;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

import static com.bee.leetcode.util.MapUtil.str2Form;
import static com.bee.leetcode.util.MapUtil.str2Json;

/**
 * created by dr_chene on 2021/3/3
 * desc
 */
public interface RegisterService {

    @POST("/user/register")
    Single<User> register(
            @Body RequestBody json
    );

    /**
     *
     * @param method {@link ServiceConstants}中method常量
     * @param registerBody method对应的phone或email账号
     *
     */
    default Single<User> register(String registerBody, String password, int authCode, String method){
        JSONObject json = new JSONObject();
        try {
            json.put("registerBody", registerBody);
            json.put("password", password);
            json.put("authCode", authCode);
            json.put("method", method);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return register(str2Json(json.toString()));
    }
}
