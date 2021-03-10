package com.bee.leetcode.db.bean;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * created by dr_chene on 2021/3/10
 * desc
 */
@Entity(tableName = "question_lists")
public class QuestionList {
    @PrimaryKey
    public long listId;
    public String listName;
    public int deleted;

    @NonNull
    @Override
    public String toString() {
        return "QuestionList{" +
                "listId=" + listId +
                ", listName='" + listName + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
