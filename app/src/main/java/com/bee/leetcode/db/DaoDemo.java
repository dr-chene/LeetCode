package com.bee.leetcode.db;

import androidx.room.Dao;

import com.bee.leetcode.net.BeanDemo;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * created by dr_chene on 2021/2/19
 * desc
 */
@Dao
public interface DaoDemo {

    Maybe<List<BeanDemo>> get();

    Completable save(BeanDemo t);
}
