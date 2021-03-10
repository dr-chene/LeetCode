package com.bee.leetcode.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.bee.leetcode.db.bean.QuestionList;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * created by dr_chene on 2021/3/10
 * desc
 */
@Dao
public interface QuestionListDao {

    @Query("SELECT * FROM question_lists")
    Single<List<List<QuestionList>>> getQuestionLists();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable saveQuestionLists(List<QuestionList> lists);

    @Query("DELETE FROM question_lists")
    Completable delete();
}
