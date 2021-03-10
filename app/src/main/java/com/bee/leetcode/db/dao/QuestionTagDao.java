package com.bee.leetcode.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.bee.leetcode.db.bean.QuestionTag;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * created by dr_chene on 2021/3/10
 * desc
 */
@Dao
public interface QuestionTagDao {

    @Query("SELECT * FROM question_tags")
    Single<List<List<QuestionTag>>> getQuestionTags();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable saveQuestionTags(List<QuestionTag> lists);

    @Query("DELETE FROM question_tags")
    Completable delete();
}
