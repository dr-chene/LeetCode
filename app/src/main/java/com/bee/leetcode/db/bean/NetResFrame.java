package com.bee.leetcode.db.bean;

public class NetResFrame<T> {
    private static int code;
    private T data;
    private String message;

    public static int getCode() {
        return code;
    }

    public static void setCode(int code) {
        NetResFrame.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
