package com.bee.leetcode.net.service;

import com.bee.leetcode.net.BeanDemo;

import io.reactivex.rxjava3.core.Single;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

import static com.bee.leetcode.util.MapUtil.str2Form;

/**
 * created by dr_chene on 2021/2/5
 * desc 忘记密码，有两种方式：phone，email
 */
public interface ForgetService {

    @Multipart
    @POST("/user/forget")
    Single<BeanDemo> forget(
            @Part("method") RequestBody method,
            @Part("forGetBody") RequestBody forGetBody,
            @Part("code") RequestBody code,
            @Part("newpassword") RequestBody newPassword
    );

    /**
     *
     * @param method,forGetBody  与{@link RegisterService}一致
     * @param code 发送的验证码
     */
    default Single<BeanDemo> forget(String method, String forGetBody, String code, String newPassword){
        return forget(str2Form(method), str2Form(forGetBody), str2Form(code), str2Form(newPassword));
    }
}
