package com.bee.leetcode.db.bean;

import androidx.annotation.NonNull;

/**
 * created by dr_chene on 2021/3/10
 * desc
 */
public class Solved {
    public int solved;
    public int mid;
    public int diff;
    public int easy;

    @NonNull
    @Override
    public String toString() {
        return "Solved{" +
                "solved=" + solved +
                ", mid=" + mid +
                ", diff=" + diff +
                ", easy=" + easy +
                '}';
    }
}
