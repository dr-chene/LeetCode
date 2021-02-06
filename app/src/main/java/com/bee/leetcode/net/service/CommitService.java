package com.bee.leetcode.net.service;

import com.bee.leetcode.net.TempBean;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * created by dr_chene on 2021/2/6
 * desc 提交记录，用户所有提交记录，提交代码进行打分
 */
public interface CommitService {

    @POST("/question/commit")
    Single<TempBean> commitCode(
            @Body String code,
            @Body String questionId
    );

    @POST("/commit/all")
    Single<TempBean> getQuestionHistories(
            @Body String questionId
    );

    @POST("/user/commit/all")
    Single<TempBean> getUserHistories();
}
