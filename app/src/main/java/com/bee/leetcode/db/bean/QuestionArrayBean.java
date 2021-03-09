package com.bee.leetcode.db.bean;

//题目列表展示接口中的Array部分

import com.google.gson.annotations.SerializedName;

public class QuestionArrayBean {

    private long questionid;
    private String title;
    private int answer_num;
    private String passrate;
    private String difficulty;
    private Boolean ispassed;

    public long getQuestionid() {
        return questionid;
    }

    public void setQuestionid(long questionid) {
        this.questionid = questionid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAnswer_num() {
        return answer_num;
    }

    public void setAnswer_num(int answer_num) {
        this.answer_num = answer_num;
    }

    public String getPassrate() {
        return passrate;
    }

    public void setPassrate(String passrate) {
        this.passrate = passrate;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Boolean getIspassed() {
        return ispassed;
    }

    public void setIspassed(Boolean ispassed) {
        this.ispassed = ispassed;
    }
}
