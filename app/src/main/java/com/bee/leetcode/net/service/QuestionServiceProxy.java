package com.bee.leetcode.net.service;

import com.bee.leetcode.db.bean.ApiResponse;
import com.bee.leetcode.db.bean.PageData;
import com.bee.leetcode.db.bean.Question;
import com.bee.leetcode.db.bean.QuestionList;
import com.bee.leetcode.net.RetrofitFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

import static com.bee.leetcode.util.MapUtil.str2Json;

/**
 * created by dr_chene on 2021/3/10
 * desc
 */
public class QuestionServiceProxy {
    private final QuestionService api;

    public QuestionServiceProxy() {
        api = RetrofitFactory.createWithNormal(QuestionService.class);
    }

    /**
     * @param listId  请求的问题的类型分组
     * @param pageNum 每页问题的数量
     */
    public Single<ApiResponse<PageData<Question>>> getQuestions(String listId, String difficulty, String status, int pageNum, int page, String tagId, String keyword) {
        JSONObject json = new JSONObject();
        try {
            json.put("listId", listId);
            json.put("difficulty", difficulty);
            json.put("status", status);
            json.put("pageNum", pageNum);
            json.put("page", page);
            json.put("tagId", tagId);
            json.put("keyword", keyword);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return api.getQuestions(str2Json(json.toString()));
    }

    //获取问题分类信息
    public Single<ApiResponse<List<QuestionList>>> getQuestionCategories() {
        return api.getQuestionCategories();
    }

    //获取问题标签信息
    public Single<ApiResponse<List<QuestionList>>> getQuestionTags() {
        return api.getQuestionTags();
    }
}
