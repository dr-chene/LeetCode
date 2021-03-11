package com.bee.leetcode.db.bean;

//单个提交记录，接收数据的时候用List<CommitHistoryBean>去接收

public class CommitHistoryBean {
    private String questionName;
    private String questionId;

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
}
