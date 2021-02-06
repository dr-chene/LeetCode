package com.bee.leetcode.net.service;

import com.bee.leetcode.net.TempBean;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * created by dr_chene on 2021/2/6
 * desc 题目获取接口
 */
public interface QuestionService {

    String QUESTION_TYPE_HOT = "hot";
    String QUESTION_TYPE_DATABASE = "database";
    String QUESTION_TYPE_ALGORITHM = "algorithm";
    String QUESTION_TYPE_COMPETITION = "competition";
    String QUESTION_TYPE_TENCENT = "tencent";
    String QUESTION_TYPE_TOP = "top";

    String DIFFICULTY_EASY = "easy";
    String DIFFICULTY_MID = "mid";
    String DIFFICULTY_DIFF = "diff";

    String STATE_NOT_DONE = "not-done";
    String STATE_ANSWERED = "answered";
    String STATE_TRIED = "tried";

    String TAG_STACK = "stack";
    String TAG_PILE = "pile";
    String TAG_GREED = "greed";
    String TAG_SORT = "sort";
    String TAG_TREE = "tree";
    String TAG_OPERATION = "operation";

    /**
     * @param type 请求的问题的类型分组
     * @param pageNum 每页问题的数量
     */
    @GET("/question/all")
    Single<TempBean> getQuestions(
            @Query("list") String type,
            @Query("difficulty") String difficulty,
            @Query("state") String state,
            @Query("tag") String tag,
            @Query("page-num") int pageNum,
            @Query("page") int page,
            @Query("keyword") String keyword
    );

    /**
     *
     * @return 返回题目详情
     */
    @GET("/question/start")
    Single<TempBean> startQuestion(
            @Query("questionId") String questionId
    );
}
