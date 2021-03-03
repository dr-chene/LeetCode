package com.bee.leetcode.util;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * created by dr_chene on 2021/3/3
 * desc 将数据映射requestBody
 */
public class MapUtil {
    private MapUtil() {
    }

    public static RequestBody str2Form(String content) {
        return str2Body(content, "multipart/form-data");
    }

    public static RequestBody int2Form(int content) {
        return str2Form(String.valueOf(content));
    }

    public static RequestBody str2Raw(String content) {
        return str2Body(content, "text/plain");
    }

    public static RequestBody int2Raw(int content) {
        return str2Raw(String.valueOf(content));
    }

    public static RequestBody str2Json(String content) {
        return str2Body(content, "application/json; charset=utf-8");
    }

    public static RequestBody int2Json(int content) {
        return str2Json(String.valueOf(content));
    }

    private static RequestBody str2Body(String content, String type) {
        return RequestBody.create(MediaType.parse(type), content);
    }
}
