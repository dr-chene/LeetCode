package com.bee.leetcode.net.service;

import com.bee.leetcode.net.BeanDemo;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * created by dr_chene on 2021/2/6
 * desc 题解，题目的所有题解
 */
public interface AnswerService {

    @GET("/answer/answerId")
    Single<BeanDemo> getAnswerDetail(
            @Query("answerId") String answerId
    );

    // TODO: 2021/3/10 暂无可用接口
    @GET("answer/all")
    Single<BeanDemo> getQuestionAnswers(
            @Query("questionId") String questionId
    );

    @POST("/answer")
    Single<BeanDemo> sendAnswer(
            @Body String userId,
            @Body String questionId,
            @Body String title,
            @Body String content,
            @Body String image, //题解封面
            @Body String createTime,
            @Body String updateTime
    );
}
