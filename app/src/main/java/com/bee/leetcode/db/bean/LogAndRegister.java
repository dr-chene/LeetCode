package com.bee.leetcode.db.bean;

public class LogAndRegister {
    private static int code;
    private Data data;
    private static String message;

    public static int getCode() {
        return code;
    }

    public static void setCode(int code) {
        LogAndRegister.code = code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static String getMessage() {
        return message;
    }

    public static void setMessage(String message) {
        LogAndRegister.message = message;
    }

public static class Data{
    private String token;
    private UserBean user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }
}
}
