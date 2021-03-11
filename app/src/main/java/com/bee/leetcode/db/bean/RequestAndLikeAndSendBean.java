package com.bee.leetcode.db.bean;

//请求验证码,点赞,评论发送,提交题解接口

public class RequestAndLikeAndSendBean {
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
