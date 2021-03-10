package com.bee.leetcode.net.service;

import com.bee.leetcode.db.bean.ApiResponse;
import com.bee.leetcode.db.bean.Commit;
import com.bee.leetcode.net.BeanDemo;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * created by dr_chene on 2021/2/6
 * desc 提交记录，用户所有提交记录，提交代码进行打分
 */
public interface CommitService {

//    @POST("/question/commit")
//    Single<BeanDemo> commitCode(
//            @Body String code,
//            @Body String questionId
//    );

    // TODO: 2021/3/10 暂无可用接口
    @POST("commit/questionId")
    Single<BeanDemo> getQuestionHistories(
            @Body String questionId
    );

    @POST("user/user/commit/all")
    Single<ApiResponse<List<Commit>>> getCommitHistories();
}
