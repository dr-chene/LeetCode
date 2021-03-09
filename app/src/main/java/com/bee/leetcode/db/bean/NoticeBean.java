package com.bee.leetcode.db.bean;

//消息通知

import com.google.gson.annotations.SerializedName;

public class NoticeBean {
    private String received_id;
    private String operator_id;
    private String operator_name;
    private String publish_date;
    private String action;
    private String state;
    private String create_time;
    private int code;

    public String getReceived_id() {
        return received_id;
    }

    public void setReceived_id(String received_id) {
        this.received_id = received_id;
    }

    public String getOperator_id() {
        return operator_id;
    }

    public void setOperator_id(String operator_id) {
        this.operator_id = operator_id;
    }

    public String getOperator_name() {
        return operator_name;
    }

    public void setOperator_name(String operator_name) {
        this.operator_name = operator_name;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
