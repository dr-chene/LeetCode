package com.bee.leetcode.db.bean;

import androidx.annotation.NonNull;

public class User {
    public String nickname;
    public String sex;
    public String avatar;

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
