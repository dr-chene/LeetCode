package com.bee.leetcode.db;

import androidx.room.Dao;

import com.bee.leetcode.db.bean.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * created by dr_chene on 2021/2/19
 * desc
 */
@Dao
public interface DaoDemo {

    Single<List<User>> get();

    Completable save(User t);
}
