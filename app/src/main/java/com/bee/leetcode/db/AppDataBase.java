package com.bee.leetcode.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.bee.leetcode.db.bean.Commit;
import com.bee.leetcode.db.bean.Question;
import com.bee.leetcode.db.bean.QuestionList;
import com.bee.leetcode.db.bean.QuestionTag;
import com.bee.leetcode.db.dao.CommitDao;
import com.bee.leetcode.db.dao.QuestionDao;
import com.bee.leetcode.db.dao.QuestionListDao;
import com.bee.leetcode.db.dao.QuestionTagDao;

@Database(entities = {Commit.class, QuestionList.class, QuestionTag.class, Question.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract CommitDao getCommitDao();
    public abstract QuestionDao getQuestionDao();
    public abstract QuestionListDao getQuestionListDao();
    public abstract QuestionTagDao getQuestionTagDao();


    private static volatile AppDataBase instance;
    public static final String DATA_BASE_NAME = "LeetCode.db";

    public static @NonNull
    AppDataBase getInstance() {
        return instance;
    }

    public static void init(Context context) {
        if (instance == null) {
            synchronized (AppDataBase.class) {
                if (instance == null) {
                    instance = buildDataBase(context);
                }
            }
        }
    }

    private static AppDataBase buildDataBase(Context context) {
        return Room.databaseBuilder(context, AppDataBase.class, DATA_BASE_NAME).build();
    }
}
