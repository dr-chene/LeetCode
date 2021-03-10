package com.bee.leetcode.db.bean;

import androidx.annotation.NonNull;

import java.util.List;

/**
 * created by dr_chene on 2021/3/10
 * desc
 */
public class PageData<T> {
    public List<T> records;
    public int total;
    public int size;
    public int current;
    //orders接口也没写清楚是什么
    public List<Object> orders;
    public boolean optimizeCountSql;
    public boolean hitCount;
    public boolean searchCount;
    public int pages;

    @NonNull
    @Override
    public String toString() {
        return "PageData{" +
                "records=" + records +
                ", total=" + total +
                ", size=" + size +
                ", current=" + current +
                ", orders=" + orders +
                ", optimizeCountSql=" + optimizeCountSql +
                ", hitCount=" + hitCount +
                ", searchCount=" + searchCount +
                ", pages=" + pages +
                '}';
    }
}
