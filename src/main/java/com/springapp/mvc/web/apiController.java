package com.springapp.mvc.web;

import com.springapp.mvc.model.Tweet;
import com.springapp.mvc.model.Users;
import com.springapp.mvc.service.DbRepositoryService;
import com.springapp.mvc.service.FollowerFollowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: maverick
 * Date: 5/8/13
 * Time: 8:17 PM
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class apiController {
    private final DbRepositoryService dbRepositoryService;
    private final FollowerFollowingService followerFollowingService;

    @Autowired
    public apiController(DbRepositoryService dbRepositoryService, FollowerFollowingService followerFollowingService) {
        this.dbRepositoryService = dbRepositoryService;
        this.followerFollowingService = followerFollowingService;
    }

    @RequestMapping(value = "/api/addPost", method = RequestMethod.POST)
    @ResponseBody
    public String addPost(HttpServletRequest request, @RequestParam("tweettext") String tweettext) {
        System.out.println("Coming to Post Sir");
        Tweet t = new Tweet();
        Users u = dbRepositoryService.fetchUserByAccessToken(request.getHeader("accessToken"));
        t.setUsername(u.getUsername());
        t.setTweettext(tweettext);
        dbRepositoryService.postTweet(t);
        return "Successfully posted on "+u.getUsername()+ " profile";
    }

    @RequestMapping(value = "/api/findFollowers", method = RequestMethod.POST)
    @ResponseBody
    public List<String> findFollowers(@RequestParam("username") String username) {
        return followerFollowingService.fetchFollowersList(username);
    }

    @RequestMapping(value = "/api/findFollowing", method = RequestMethod.POST)
    @ResponseBody
    public List<String> findFollowing(@RequestParam("username") String username) {
        return followerFollowingService.fetchFollowingList(username);
    }
}

