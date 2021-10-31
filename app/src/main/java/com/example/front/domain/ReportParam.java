package com.example.front.domain;

public class ReportParam {
    long user_id;
    String content;

    public long getUser_id() {
        return user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}
