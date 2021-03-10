package com.bee.leetcode.net.service;

import com.bee.leetcode.db.bean.ApiResponse;
import com.bee.leetcode.db.bean.PageData;
import com.bee.leetcode.db.bean.Question;
import com.bee.leetcode.db.bean.QuestionDetail;
import com.bee.leetcode.db.bean.QuestionList;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * created by dr_chene on 2021/2/6
 * desc 获取题目
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

    @GET("question/question/all")
    Single<ApiResponse<PageData<Question>>> getQuestions(
            @Body RequestBody json
    );

    @GET("question/get-list")
    Single<ApiResponse<List<QuestionList>>> getQuestionCategories();

    @GET("question/get-tag")
    Single<ApiResponse<List<QuestionList>>> getQuestionTags();

    /**
     * @return 随机返回题目详情
     */
    @GET("question/start/random")
    Single<ApiResponse<QuestionDetail>> startQuestion();

    /**
     * @return 按questionId返回题目详情
     */
    @GET("question/start/{questionId}")
    Single<ApiResponse<QuestionDetail>> startQuestion(@Path("questionId") long questionId);
}
