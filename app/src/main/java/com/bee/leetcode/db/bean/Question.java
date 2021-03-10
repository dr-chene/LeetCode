package com.bee.leetcode.db.bean;

//提交记录接口中的Array中的字段

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "questions")
public class Question {
    @PrimaryKey
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
