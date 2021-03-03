package com.bee.leetcode.db.bean;

import com.google.gson.annotations.SerializedName;

/**
 * created by dr_chene on 2021/3/3
 * desc
 */
public class User {
    @SerializedName(value = "name", alternate = "nickname")
    public String nickname;
    public String age;
    public String sex;
    @SerializedName("avater")
    public String avatar;
}
