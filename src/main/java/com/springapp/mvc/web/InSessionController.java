package com.springapp.mvc.web;

import com.google.gson.Gson;
import com.springapp.mvc.service.DbRepositoryService;
import com.springapp.mvc.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class InSessionController {

    private final DbRepositoryService dbRepositoryService;

    @Autowired
    public InSessionController(DbRepositoryService dbRepositoryService) {
        this.dbRepositoryService = dbRepositoryService;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String printHomePage(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        model.addAttribute("message", "Hello "+name);

        List<Tweet> tweets= dbRepositoryService.fetchTimeLine(name);
        model.addAttribute("list",tweets);

        return "homepage";
    }

    @RequestMapping(value = "/{username}/timeline", method = RequestMethod.GET)
    public String printTimelineOfAnyUser(@PathVariable("username") String username, ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
         model.addAttribute("loggedInUser",name);
        if(name.compareTo(username)==0)
        {
            model.addAttribute("message", "Hello. Welcome to your profile"+name);
            model.addAttribute("result",0);
        }
        else{
            model.addAttribute("message", "Hello "+name+". Welcome to"+username+" profile!!!");
            model.addAttribute("result",dbRepositoryService.findRelation(username, name));
        }

        List<Tweet> tweets= dbRepositoryService.fetchUsersTweets(username);
        model.addAttribute("list", tweets);

        return "showTimeline";
    }

    @RequestMapping(value = "/{username}/newTweets", method = RequestMethod.GET)
    @ResponseBody
    public String fetchNewUsersTweets(@PathVariable("username") String username, ModelMap modelMap) {
        List<Tweet> tweets= dbRepositoryService.fetchUsersTweets(username);

        modelMap.addAttribute("list", tweets);
        modelMap.addAttribute("username", username);
        String jsonString = new Gson().toJson(tweets);
        return jsonString;
    }


    @RequestMapping(value = "/{username}/following", method = RequestMethod.GET)
    public String fetchFollowingList(@PathVariable("username") String username, ModelMap modelMap) {
        List<String> users= dbRepositoryService.findFollowedList(username);
        modelMap.addAttribute("list", users);
        modelMap.addAttribute("username", username);
        return "following";
    }


    @RequestMapping(value = "/{username}/followers", method = RequestMethod.GET)
    public String fetchFollowersList(@PathVariable("username") String username, ModelMap modelMap) {
        List<String> users= dbRepositoryService.findFollowersList(username);
        modelMap.addAttribute("list", users);
        modelMap.addAttribute("username", username);
        return "followers";
    }

    @RequestMapping(value = "/{username}/changeFollowingStatus", method = RequestMethod.GET)
    @ResponseBody
    public String changeFollowingStatus(@PathVariable("username") String username){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();

        System.out.println("reaching here");
        String s = dbRepositoryService.changeFollowingStatus(name, username);
        System.out.println(s);
        return s;
    }

    //Kartik posting tweets
    @RequestMapping(value="/{username}/timeline",method = RequestMethod.POST)
    @ResponseBody
    public  void postTweet(@ModelAttribute("tweet") Tweet tweet, BindingResult result)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        tweet.setUsername(userName);
        dbRepositoryService.postTweet(tweet);
    }

    @RequestMapping(value="/home",method = RequestMethod.POST)
    @ResponseBody
    public  void postTweetHome(@ModelAttribute("tweet") Tweet tweet, BindingResult result)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        tweet.setUsername(userName);
        dbRepositoryService.postTweet(tweet);
    }
}