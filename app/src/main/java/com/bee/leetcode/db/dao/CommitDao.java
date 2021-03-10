package com.bee.leetcode.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.bee.leetcode.db.bean.Commit;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * created by dr_chene on 2021/3/10
 * desc
 */
@Dao
public interface CommitDao {

    @Query("SELECT * FROM commits")
    Single<List<List<Commit>>> getCommits();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable saveCommit(List<Commit> commits);

    @Query("DELETE FROM commits WHERE commitTime = :commitTime")
    Completable delete(String commitTime);
}
