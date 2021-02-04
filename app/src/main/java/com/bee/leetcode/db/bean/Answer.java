package com.bee.leetcode.db.bean;

public class Answer {
    private static int code;
    private Data data;
    private static String message;

    public static int getCode() {
        return code;
    }

    public static void setCode(int code) {
        Answer.code = code;
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
        Answer.message = message;
    }

    public static class Data{
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
}
