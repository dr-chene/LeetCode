package com.bee.leetcode.net.service;

import com.bee.leetcode.net.BeanDemo;

import io.reactivex.rxjava3.core.Single;
import okhttp3.RequestBody;
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

    // TODO: 2021/3/10  暂无可用接口
    @POST("/comment/add")
    Single<BeanDemo> sendComment(
            @Body RequestBody parentId,
            @Body RequestBody userId,
            @Body RequestBody content
    );

    // TODO: 2021/3/10 暂无可用接口
    @GET("/comment/reply")
    Single<BeanDemo> getCommentReply(
            @Query("parentId") long commentId
    );

//    /**
//     *
//     * @param category 评论的分类：hot，early，new
//     */
//    @POST("/comment/all")
//    Single<BeanDemo> getQuestionComment(
//            @Query("questionId") long questionId,
//            @Query("category") String category
//    );

    // TODO: 2021/3/10 暂无可用接口

    /**
     * a
     *
     * @param pageNum 请求的页数
     */
    @POST("/comment/get")
    Single<BeanDemo> getQuestionComment(
            @Body RequestBody pageNum,
            @Body RequestBody parentId
    );

    // TODO: 2021/3/10 暂无可用接口
    @GET("/question/comment/questionId/anwserId")
    Single<BeanDemo> getAnswerComment(
            @Query("questionId") String questionId,
            @Query("anwserId") String answerId
    );
}
