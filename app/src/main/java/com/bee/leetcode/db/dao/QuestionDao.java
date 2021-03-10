package com.bee.leetcode.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.bee.leetcode.db.bean.Question;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * created by dr_chene on 2021/3/10
 * desc
 */
@Dao
public interface QuestionDao {

    @Query("SELECT * FROM questions WHERE difficulty = :difficulty AND title LIKE '%'||:keyword||'%' ORDER BY questionId LIMIT :page * :pageNum")
    Single<List<List<Question>>> getQuestions(
            String difficulty,
            int pageNum,
            int page,
            String keyword
    );

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable saveQuestions(List<Question> questions);

    @Query("DELETE FROM questions WHERE questionId = :questionId")
    Completable deleteById(long questionId);
}
