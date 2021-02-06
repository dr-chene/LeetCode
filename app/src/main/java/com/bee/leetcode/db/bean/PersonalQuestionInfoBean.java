package com.bee.leetcode.db.bean;

//个人做题信息
public class PersonalQuestionInfoBean {
        private int code;
        private int solved;
        private int easy;
        private int mid;
        private int diff;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public int getSolved() {
            return solved;
        }

        public void setSolved(int solved) {
            this.solved = solved;
        }

        public int getEasy() {
            return easy;
        }

        public void setEasy(int easy) {
            this.easy = easy;
        }

        public int getMid() {
            return mid;
        }

        public void setMid(int mid) {
            this.mid = mid;
        }

        public int getDiff() {
            return diff;
        }

        public void setDiff(int diff) {
            this.diff = diff;
        }
    }

