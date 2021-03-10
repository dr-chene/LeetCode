package com.bee.leetcode.db.bean;

//题解列表展示接口中内层data中的数据

import androidx.annotation.NonNull;

public class Commit {
    public long userId;
    public long questionId;
    public String title;
    public String commitResult;
    public String commitTime;

    @NonNull
    @Override
    public String toString() {
        return "Question{" +
                "userId=" + userId +
                ", questionId=" + questionId +
                ", title='" + title + '\'' +
                ", commitResult='" + commitResult + '\'' +
                ", commitTime='" + commitTime + '\'' +
                '}';
    }
}
