package com.bee.leetcode.db.bean;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * created by dr_chene on 2021/3/10
 * desc
 */
@Entity(tableName = "question_tags")
public class QuestionTag {
    @PrimaryKey
    public long tagId;
    public String tagName;
    public int deleted;

    @NonNull
    @Override
    public String toString() {
        return "QuestionTag{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
