package com.bee.leetcode.db.bean;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * created by dr_chene on 2021/3/10
 * desc
 */
public class QuestionListAndTag {
    @SerializedName(value = "listId", alternate = "tagId")
    public long id;
    @SerializedName(value = "listName", alternate = "tagName")
    public String name;
    public int deleted;

    @NonNull
    @Override
    public String toString() {
        return "QuestionListAndTag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
