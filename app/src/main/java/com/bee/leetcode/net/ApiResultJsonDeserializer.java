package com.bee.leetcode.net;

import com.bee.leetcode.repository.DataRequest;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * created by dr_chene on 2021/3/4
 * desc 网络返回数据解析，解决请求失败data为string的问题
 */
public class ApiResultJsonDeserializer implements JsonDeserializer<DataRequest.ApiResponse<?>> {

    @Override
    public DataRequest.ApiResponse<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        DataRequest.ApiResponse<?> response = new DataRequest.ApiResponse<>();
        if (json.isJsonObject()) {
            JsonObject jsonObject = json.getAsJsonObject();
            int code = jsonObject.get("code").getAsInt();
            response.code = code;
            response.message = jsonObject.get("message").getAsString();
            if (code != 200) {
                response.data = context.deserialize(jsonObject.get("data"), String.class);
                return response;
            }
            Type itemType = ((ParameterizedType) typeOfT).getActualTypeArguments()[0];
            response.data = context.deserialize(jsonObject.get("data"), itemType);
            return response;
        }
        return response;
    }
}
