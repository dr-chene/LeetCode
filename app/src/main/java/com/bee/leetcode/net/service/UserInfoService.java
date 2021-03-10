package com.bee.leetcode.net.service;

import com.bee.leetcode.db.bean.ApiResponse;

import io.reactivex.rxjava3.core.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


/**
 * created by dr_chene on 2021/2/6
 * desc 用户相关
 */
public interface UserInfoService {

    @POST("user/update-info")
    Single<ApiResponse<String>> updateBaseInfo(
            @Body RequestBody json
    );

    @POST("user/update-account")
    Single<ApiResponse<String>> updateAccountInfo(
            @Body RequestBody json
    );

    //val file = File(picture) // picture是图片路径，通过路径生成file文件
    //        val requestBody: RequestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), file)
    //        // avatar参数是一个自定义的名字，自己随便写
    //        return MultipartBody.Part.createFormData("avatar", file.name, requestBody)
    @Multipart
    @POST("upload")
    Single<ApiResponse<String>> updateAvatar(
            @Part MultipartBody.Part img
    );
}
