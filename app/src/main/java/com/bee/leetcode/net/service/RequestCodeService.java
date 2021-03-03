package com.bee.leetcode.net.service;

import com.bee.leetcode.net.BeanDemo;

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
public interface RequestCodeService {

    @POST("/user/requestcode")
    Single<BeanDemo> getCode(
            @Body RequestBody json
    );

    /**
     *
     * @param method 获取验证码方式 {@link com.bee.leetcode.net.ServiceConstants}
     * @param number method对应的phone或者email账号
     */
    default Single<BeanDemo> getCode(String method, String number){
        JSONObject json = new JSONObject();
        try {
            json.put("method", method);
            json.put("number", number);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getCode(str2Json(json.toString()));
    }
}