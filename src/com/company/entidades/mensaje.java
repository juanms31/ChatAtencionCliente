package com.company.entidades;

public class mensaje {
    private String from;
    private String date;
    private String msg;
    private String color;

    public mensaje(String from, String date, String msg, String color) {
        this.from = from;
        this.date = date;
        this.msg = msg;
        this.color = color;
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
        return  "<p> " +
                "<span style=" +
                color +
                    from + "[" + date + "]" + ": " + msg +
                "</br>" +
                "</p>";
    }
}
