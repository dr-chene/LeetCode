package com.bee.leetcode.db.bean;

import java.util.List;
//题目列表展示
public class AllQuestion {

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

