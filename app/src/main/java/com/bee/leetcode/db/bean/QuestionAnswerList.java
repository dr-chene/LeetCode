package com.bee.leetcode.db.bean;

//题解列表展示接口

import java.util.List;

public class QuestionAnswerList {
    private String message;
    private int code;
    private List<QuestionAnswerBean> data;

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

    public List<QuestionAnswerBean> getData() {
        return data;
    }

    public void setData(List<QuestionAnswerBean> data) {
        this.data = data;
    }
}
