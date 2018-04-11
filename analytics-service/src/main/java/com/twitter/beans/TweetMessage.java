package com.twitter.beans;

import java.util.Date;

public class TweetMessage {

    private String userMention;
    private Date date;
    private long time;
    private String user;
    private String text;
    private String language;

    public String getUserMention() {
        return userMention;
    }

    public void setUserMention(String userMention) {
        this.userMention = userMention;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
