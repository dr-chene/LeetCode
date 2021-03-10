package com.bee.leetcode.db.bean;

//提交记录接口中的Array中的字段

import androidx.annotation.NonNull;

public class Question {
    public long questionId;
    public String title;
    public int answerNum;
    public String passRate;
    public String difficulty;

    @NonNull
    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", title='" + title + '\'' +
                ", answerNum=" + answerNum +
                ", passRate='" + passRate + '\'' +
                ", difficulty='" + difficulty + '\'' +
                '}';
    }
}
