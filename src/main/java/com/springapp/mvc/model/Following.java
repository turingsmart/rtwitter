package com.springapp.mvc.model;

import com.sun.istack.internal.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: maverick
 * Date: 18/7/13
 * Time: 5:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class Following {

    @NotNull
    private String follower;

    @NotNull
    private String followed;

    @NotNull
    private String latesttimeoffollowing;

    private String latesttimeofunfollowing;

    public String getLatesttimeoffollowing() {
        return latesttimeoffollowing;
    }

    public void setLatesttimeoffollowing(String latesttimeoffollowing) {
        this.latesttimeoffollowing = latesttimeoffollowing;
    }

    public String getFollowed() {
        return followed;
    }

    public void setFollowed(String followed) {
        this.followed = followed;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getLatesttimeofunfollowing() {
        return latesttimeofunfollowing;
    }

    public void setLatesttimeofunfollowing(String latesttimeofunfollowing) {
        this.latesttimeofunfollowing = latesttimeofunfollowing;
    }
}
