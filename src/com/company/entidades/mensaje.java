package com.company.entidades;

public class mensaje {
    private String from;
    private String date;
    private String msg;

    public mensaje(String from, String date, String msg) {
        this.from = from;
        this.date = date;
        this.msg = msg;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return from + "[" + date + "]" + ": " + msg;
    }
}
