package com.bee.leetcode.net.service;

import com.bee.leetcode.net.BeanDemo;
import com.bee.leetcode.net.ServiceConstants;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.rxjava3.core.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

import static com.bee.leetcode.util.MapUtil.str2Form;
import static com.bee.leetcode.util.MapUtil.str2Json;

/**
 * created by dr_chene on 2021/2/6
 * desc 用户相关
 */
public interface UserInfoService {

    @POST("/user/update-info")
    Single<BeanDemo> updateBaseInfo(
            @Body RequestBody json
    );

    @POST("/user/update-account")
    Single<BeanDemo> updateAccountInfo(
            @Body RequestBody json
    );

    //val file = File(picture) // picture是图片路径，通过路径生成file文件
    //        val requestBody: RequestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), file)
    //        // avatar参数是一个自定义的名字，自己随便写
    //        return MultipartBody.Part.createFormData("avatar", file.name, requestBody)
    @Multipart
    @POST("/user/update-avater")
    Single<BeanDemo> updateAvatar(
            @Part MultipartBody.Part img
    );
}
