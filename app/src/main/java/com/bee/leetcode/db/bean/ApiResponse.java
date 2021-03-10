package com.bee.leetcode.db.bean;

import androidx.annotation.NonNull;

/**
 * created by dr_chene on 2021/3/10
 * desc 返回的网络数据格式
 */
public class ApiResponse<T> {
    public T data;
    public int code;
    public String message;

    @NonNull
    @Override
    public String toString() {
        return "ApiResponse{" +
                "data=" + data +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
