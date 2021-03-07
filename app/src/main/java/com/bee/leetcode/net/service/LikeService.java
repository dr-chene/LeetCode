package com.bee.leetcode.net.service;

import com.bee.leetcode.net.BeanDemo;

import io.reactivex.rxjava3.core.Single;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

import static com.bee.leetcode.util.MapUtil.str2Json;

/**
 * created by dr_chene on 2021/2/6
 * desc 点赞
 */
public interface LikeService {

    String LIKE_TARGET_COMMENT = "comment";
    String LIKE_TARGET_QUESTION = "question";
    String LIKE_TARGET_ANSWER = "answer";

    @POST("/common/like")
    Single<BeanDemo> like(
            @Body RequestBody target,
            @Body RequestBody isLike,
            @Body RequestBody targetId
    );

    /**
     *
     * @param target 点赞目标的类型：题目、评论、题解
     */
    default Single<BeanDemo> like(String target, Boolean isLike, String targetId){
        return like(str2Json(target), str2Json(String.valueOf(isLike)), str2Json(targetId));
    }
}
