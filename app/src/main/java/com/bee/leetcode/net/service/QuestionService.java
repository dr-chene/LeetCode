package com.bee.leetcode.net.service;

import com.bee.leetcode.net.BeanDemo;

import io.reactivex.rxjava3.core.Single;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.Part;
import retrofit2.http.Query;

import static com.bee.leetcode.util.MapUtil.str2Form;

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

    @Multipart
    @GET("/question/all")
    Single<BeanDemo> getQuestions(
            @Part("list") RequestBody list,
            @Part("difficulty") RequestBody difficulty,
            @Part("state") RequestBody state,
            @Part("tag") RequestBody tag,
            @Part("page-num") RequestBody pageNum,
            @Part("page") RequestBody page,
            @Part("keyword") RequestBody keyword
    );

    /**
     * @param list    请求的问题的类型分组
     * @param pageNum 每页问题的数量
     */
    default Single<BeanDemo> getQuestions(String list, String difficulty, String state, String tag, int pageNum, int page, String keyword) {
        return getQuestions(str2Form(list), str2Form(difficulty), str2Form(state), str2Form(tag), str2Form(pageNum + ""), str2Form(page + ""), str2Form(keyword));
    }

    /**
     * @return 返回题目详情
     */
    @GET("/question/start")
    Single<BeanDemo> startQuestion(
            @Query("questionId") String questionId
    );
}
