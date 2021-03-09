package com.bee.leetcode.net.service;

import com.bee.leetcode.net.BeanDemo;

import io.reactivex.rxjava3.core.Single;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static com.bee.leetcode.util.MapUtil.int2Raw;
import static com.bee.leetcode.util.MapUtil.str2Raw;

/**
 * created by dr_chene on 2021/2/6
 * desc 发送评论，评论获取
 */
public interface CommentService {

    String COMMENT_CATEGORY_HOT = "hot";
    String COMMENT_CATEGORY_EARLY = "early";
    String COMMENT_CATEGORY_NEW = "new";

    @POST("/comment/add")
    Single<BeanDemo> sendComment(
            @Body RequestBody parentId,
            @Body RequestBody userId,
            @Body RequestBody content
    );

    default Single<BeanDemo> sendComment(int parentId, int userId, String content){
        return sendComment(str2Raw(parentId +  ""), str2Raw(userId + ""), str2Raw(content));
    }

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

    /**
     *
     * @param pageNum 请求的页数
     */
    @POST("/comment/get")
    Single<BeanDemo> getQuestionComment(
            @Body RequestBody pageNum,
             @Body RequestBody parentId
    );

    default Single<BeanDemo> getQuestionComment(int parentId, int pageNum){
        return getQuestionComment(str2Raw(pageNum + ""), str2Raw(parentId + ""));
    }

    @GET("/question/comment/questionId/anwserId")
    Single<BeanDemo> getAnswerComment(
        @Query("questionId") String questionId,
        @Query("anwserId") String answerId
    );
}
