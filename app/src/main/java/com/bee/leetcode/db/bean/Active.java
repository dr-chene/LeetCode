package com.bee.leetcode.db.bean;

import androidx.annotation.NonNull;

/**
 * created by dr_chene on 2021/3/10
 * desc
 */
public class Active {
    public long userId;
    public String commitTime;
    public String commitTimes;

    @NonNull
    @Override
    public String toString() {
        return "Active{" +
                "userId=" + userId +
                ", commitTime='" + commitTime + '\'' +
                ", commitTimes='" + commitTimes + '\'' +
                '}';
    }
}
