package com.bee.leetcode.net.service;

import com.bee.leetcode.db.bean.ApiResponse;
import com.bee.leetcode.db.bean.Solved;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

/**
 * created by dr_chene on 2021/2/6
 * desc 个人已完成题目信息
 */
public interface SolvedService {

    @GET("question/user/answer")
    Single<ApiResponse<Solved>> getSolved();
}
