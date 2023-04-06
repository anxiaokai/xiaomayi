package com.tencent.wxcloudrun.model;

public class ReplyRequestModel {
    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "ReplyRequestModel{" +
                "action='" + action + '\'' +
                '}';
    }
}
