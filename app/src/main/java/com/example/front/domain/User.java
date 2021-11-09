package com.example.front.domain;

import java.io.Serializable;

public class User implements Serializable {
    long uid;
    String id;
    String pw;
    String name;
    int point;
    int ticket;

    public long getUid() {
        return uid;
    }

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }

    public String getName() {
        return name;
    }

    public int getPoint() {
        return point;
    }

    public int getTicket() {
        return ticket;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }
}
