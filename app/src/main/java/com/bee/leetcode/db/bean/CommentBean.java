package com.bee.leetcode.db.bean;

//评论获取(包括题解评论)

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommentBean {
    protected long userId;
    protected String userName;
    protected String avatar;
    protected int thumbup ;//点赞数量
    protected boolean isLike;
    protected String content;
    private List<SubCommentBean> subContent;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getThumbup() {
        return thumbup;
    }

    public void setThumbup(int thumbup) {
        this.thumbup = thumbup;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<SubCommentBean> getSubContent() {
        return subContent;
    }

    public void setSubContent(List<SubCommentBean> subContent) {
        this.subContent = subContent;
    }
}
