package com.springapp.mvc.model;

import com.sun.istack.internal.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: maverick
 * Date: 18/7/13
 * Time: 5:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class Tweet {

    @NotNull
    private int tweetid;

    @NotNull
    private String tweettext;

    @NotNull
    private String timestamp;

    @NotNull
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTweettext() {
        return tweettext;
    }

    public void setTweettext(String tweettext) {
        this.tweettext = tweettext;
    }

    public int getTweetid() {
        return tweetid;
    }

    public void setTweetid(int tweetid) {
        this.tweetid = tweetid;
    }
}
