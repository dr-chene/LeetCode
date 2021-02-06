package com.bee.leetcode.net.service;

import com.bee.leetcode.net.TempBean;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * created by dr_chene on 2021/2/6
 * desc 发送评论，评论获取
 */
public interface CommentService {

    String COMMENT_CATEGORY_HOT = "hot";
    String COMMENT_CATEGORY_EARLY = "early";
    String COMMENT_CATEGORY_NEW = "new";

    /**
     *
     * @param parentId 评论的id，个人推测意为id
     */
    @POST("/comment")
    Single<TempBean> sendComment(
            @Body int parentId,
            @Body String type,
            @Body String content
    );

    /**
     *
     * @param category 评论的分类：hot，early，new
     */
    @POST("/comment/all")
    Single<TempBean> getQuestionComment(
            @Query("questionId") long questionId,
            @Query("category") String category
    );

    @GET("/question/comment/questionId/anwserId")
    Single<TempBean> getAnswerComment(
        @Query("questionId") String questionId,
        @Query("anwserId") String answerId
    );
}
