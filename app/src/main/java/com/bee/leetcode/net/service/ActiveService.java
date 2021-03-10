package com.bee.leetcode.net.service;

import com.bee.leetcode.db.bean.Active;
import com.bee.leetcode.db.bean.ApiResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

/**
 * created by dr_chene on 2021/2/6
 * desc 活跃度
 */
public interface ActiveService {

    @GET("user/user/activity")
    Single<ApiResponse<List<Active>>> getActivity();
}
