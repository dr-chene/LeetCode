package com.bee.leetcode.db.bean;

public class RequestCode {
    private static String code;
    private static Data data;

    public static String getCode() {
        return code;
    }

    public static void setCode(String code) {
        RequestCode.code = code;
    }

    public static Data getData() {
        return data;
    }

    public static void setData(Data data) {
        RequestCode.data = data;
    }

    public static class Data{
        private String code;
        private String message;
        private String number;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }
    }
}
