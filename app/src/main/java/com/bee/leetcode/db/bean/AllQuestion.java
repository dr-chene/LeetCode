package com.bee.leetcode.db.bean;

import java.util.List;

public class AllQuestion {
    private static int code;
    private  Data data;

    public static int getCode() {
        return code;
    }

    public static void setCode(int code) {
        AllQuestion.code = code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data{
        private String message;
        private int code;
        private List<QuestionArray> data;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public List<QuestionArray> getData() {
            return data;
        }

        public void setData(List<QuestionArray> data) {
            this.data = data;
        }
    }
}
