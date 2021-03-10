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

    public static RequestBody str2Json(String content) {
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), content);
    }
}
