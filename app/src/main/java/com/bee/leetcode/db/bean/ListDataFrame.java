package com.bee.leetcode.db.bean;

import java.util.List;

//题目列表展示与题解列表，泛型中传入QuestionAnswerBean或者QuestionArrayBean

public class ListDataFrame<T> {

        private String message;
        private int code;
        private List<T> data;

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

        public List<T> getData() {
            return data;
        }

        public void setData(List<T> data) {
            this.data = data;
        }
    }

