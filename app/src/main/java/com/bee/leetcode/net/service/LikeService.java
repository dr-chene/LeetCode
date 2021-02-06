package com.bee.leetcode.net.service;

import com.bee.leetcode.net.TempBean;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * created by dr_chene on 2021/2/6
 * desc 点赞
 */
public interface LikeService {

    String LIKE_TARGET_COMMENT = "comment";
    String LIKE_TARGET_QUESTION = "question";
    String LIKE_TARGET_ANSWER = "answer";

    /**
     *
     * @param target 点赞目标的类型：题目、评论、题解
     */
    @POST("/common/like")
    Single<TempBean> like(
            @Body String target,
            @Body Boolean isLike,
            @Body String targetId
    );
}
