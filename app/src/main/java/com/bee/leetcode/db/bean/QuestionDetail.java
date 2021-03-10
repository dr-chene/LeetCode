package com.bee.leetcode.db.bean;

//题目列表展示接口中的Array部分

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

public class QuestionDetail {
    public String passRate;
    public String updateTime;
    public String title;
    public String content;
    public String difficulty;
    public int commentNum;
    public String commitBody;
    public int commitNum;
    public String createTime;
    public int answerNum;
    public String questionNum;
    public int state;
    @SerializedName("thumbup")
    public int thumbUp;

    @NonNull
    @Override
    public String toString() {
        return "QuestionDetail{" +
                "passRate='" + passRate + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", commentNum=" + commentNum +
                ", commitBody='" + commitBody + '\'' +
                ", commitNum=" + commitNum +
                ", createTime='" + createTime + '\'' +
                ", answerNum=" + answerNum +
                ", questionNum='" + questionNum + '\'' +
                ", state=" + state +
                ", thumbUp=" + thumbUp +
                '}';
    }
}
