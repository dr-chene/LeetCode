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

    /**
     * @param sex    1 boy, 0 girl
     * @param avatar 头像地址，通过上传头像接口获得
     */
    default Single<BeanDemo> updateBaseInfo(String nickname, int sex, String avatar, String password) {
        JSONObject json = new JSONObject();
        try {
            json.put("nickname", nickname);
            json.put("password", password);
            json.put("sex", sex);
            json.put("avatar", avatar);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return updateBaseInfo(str2Json(json.toString()));
    }

    @Multipart
    @POST("/user/update-account")
    Single<BeanDemo> updateAccountInfo(
            @Body RequestBody json
    );

    /**
     * @param method {@link ServiceConstants}中method常量
     */
    default Single<BeanDemo> updateAccountInfo(String method, String code, String oldAccount, String newAccount) {
        JSONObject json = new JSONObject();
        try {
            json.put("newAccount", newAccount);
            json.put("oldAccount", oldAccount);
            json.put("code", code);
            json.put("method", method);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return updateAccountInfo(str2Json(json.toString()));
    }

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
