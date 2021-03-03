package com.bee.leetcode.net.service;

import com.bee.leetcode.net.BeanDemo;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

/**
 * created by dr_chene on 2021/2/6
 * desc 个人已完成题目信息
 */
public interface SolvedService {

    @GET("/user/answer")
    Single<BeanDemo> getSolved();
}
