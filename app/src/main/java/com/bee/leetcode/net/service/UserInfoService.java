package com.bee.leetcode.net.service;

import com.bee.leetcode.net.TempBean;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * created by dr_chene on 2021/2/6
 * desc
 */
public interface UserInfoService {

    /**
     *
     * @param sex 1 boy, 0 girl
     */
    @POST("/user/update-info")
    Single<TempBean> updateBaseInfo(
            @Body String nickname,
            @Body int sex,
            @Body String avatar,
            @Body String password
    );

    /**
     *
     * @param target phone/email
     */
    @POST("/user/update-account")
    Single<TempBean> updateAccountInfo(
            @Body String target,
            @Body String code,
            @Body String value
    );

    // TODO: 2021/2/6 图片二进制数据上传
    @POST("/user/update-avater")
    Single<TempBean> updateAvatar();
}
