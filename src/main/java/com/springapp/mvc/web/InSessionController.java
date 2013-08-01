package com.springapp.mvc.web;

import com.google.gson.Gson;
import com.springapp.mvc.model.Users;
import com.springapp.mvc.service.DbRepositoryService;
import com.springapp.mvc.model.Tweet;
import com.springapp.mvc.service.FollowerFollowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.lang.Math.min;

@Controller
@RequestMapping("/")
public class InSessionController {

    private final DbRepositoryService dbRepositoryService;
    private final FollowerFollowingService followerFollowingService;

    @Autowired
    public InSessionController(DbRepositoryService dbRepositoryService, FollowerFollowingService followerFollowingService) {
        this.dbRepositoryService = dbRepositoryService;
        this.followerFollowingService = followerFollowingService;

    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String printHomePage(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        model.addAttribute("message", "Hello "+name);
        model.addAttribute("username",name);

        List<Tweet> tweets= dbRepositoryService.fetchTimeLine(name,0);
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

    @RequestMapping(value = "/{username}/newTweets", method = RequestMethod.POST, produces="application/json")
    @ResponseBody
    public String fetchNewUsersTweets(@PathVariable("username") String username, @RequestParam("tweetSeen") String tweetSeen, ModelMap modelMap) {
        List<Tweet> tweets= dbRepositoryService.fetchTimeLine(username, Integer.parseInt(tweetSeen));

        modelMap.addAttribute("list", tweets);
        modelMap.addAttribute("username", username);
        String jsonString = new Gson().toJson(tweets);
        //System.out.print(jsonString);
        return jsonString;
    }

    @RequestMapping(value = "/{username}/areThereNewTweets", method = RequestMethod.POST)
    @ResponseBody
    public String findIfThereAreNewTweets(@PathVariable("username") String username, @RequestParam("firstTweetOnPage") String firstTweetOnPage){
        return dbRepositoryService.findIfThereAreNewTweets(username, firstTweetOnPage);
    }


//
//    @RequestMapping(value = "/{username}/following", method = RequestMethod.GET)
//    public String fetchFollowingList(@PathVariable("username") String username, ModelMap modelMap) {
//        List<String> users= dbRepositoryService.findFollowedList(username);
//        modelMap.addAttribute("list", users);
//        modelMap.addAttribute("username", username);
//        return "following";
//    }

    //Shashank
    @RequestMapping(value = "/{username}/following", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String fetchFollowingList(@PathVariable("username") String username, @RequestParam("followingSeen") String followingSeen, ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        modelMap.addAttribute("loggedinUser",name);

        List<String> followingListOfLoggedinUser=followerFollowingService.fetchFollowingList(name);
        List<String> followingListOfUser=new LinkedList<String>();
        if(username.compareTo(name)!=0)
        {
            followingListOfUser=followerFollowingService.fetchFollowingList(username);
            followingListOfLoggedinUser.retainAll(followingListOfUser);
            followingListOfUser.removeAll(followingListOfLoggedinUser);
            modelMap.addAttribute("followList",followingListOfUser);
        }
        for(String s:followingListOfUser)
        {
            followingListOfLoggedinUser.add(s);
        }
        followingListOfLoggedinUser.subList(Integer.parseInt(followingSeen)*10, min(Integer.parseInt(followingSeen)*10+10,followingListOfLoggedinUser.size()-1));
        String json=new Gson().toJson(followingListOfLoggedinUser);
        System.out.println(json);
        return json;
    }


//    @RequestMapping(value = "/{username}/followers", method = RequestMethod.GET)
//    public String fetchFollowersList(@PathVariable("username") String username, ModelMap modelMap) {
//        List<String> users= dbRepositoryService.findFollowersList(username);
//        modelMap.addAttribute("list", users);
//        modelMap.addAttribute("username", username);
//        return "followers";
//    }
    @RequestMapping(value = "/{username}/followers", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String fetchFollowersList(@PathVariable("username") String username, @RequestParam("followersSeen") String followersSeen, ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        modelMap.addAttribute("loggedinUser",name);

        List<String> followingListOfLoggedinUser=followerFollowingService.fetchFollowingList(name);

        List<String> followerListOfUser=followerFollowingService.fetchFollowersList(username);
        followingListOfLoggedinUser.retainAll(followerListOfUser);
        followerListOfUser.removeAll(followingListOfLoggedinUser);
        modelMap.addAttribute("followList",followerListOfUser);

        modelMap.addAttribute("unfollowList",followingListOfLoggedinUser);
        modelMap.addAttribute("username", username);
        for(String s:followerListOfUser)
        {
            followingListOfLoggedinUser.add(s);
        }
        followingListOfLoggedinUser.subList(Integer.parseInt(followersSeen)*10, min(Integer.parseInt(followersSeen)*10+10,followingListOfLoggedinUser.size()-1));
        String json=new Gson().toJson(followingListOfLoggedinUser);
        System.out.println(json);
        return json;
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

    @RequestMapping(value="/home/search",method = RequestMethod.POST)
    public  String search(@ModelAttribute("searchString") String searchString,ModelMap modelMap)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        modelMap.addAttribute("searchString",searchString);
        modelMap.addAttribute("loggedinUser",userName);
        return "search";
    }

    @RequestMapping(value = "/home/searchUser",method=RequestMethod.POST)
    @ResponseBody
    public String searchResults(@RequestParam("loggedinUser") String userName, @RequestParam("searchString") String searchString,ModelMap modelMap)
    {
        //  System.out.println("here......."+userName +" "+ searchString);
        List<Users> users=followerFollowingService.findUsersWithName(searchString);
        //List<Users> users=new LinkedList<Users>();
        List<String> followingListOfLoggedinUser=followerFollowingService.fetchFollowingList(userName);

        Map<String,Users> mappingOfUsers=new HashMap<String, Users>();
        List<String> namesOfUsers = new LinkedList<String>();
        for(Users u:users)
        {
            // System.out.println(u.getUsername());
            namesOfUsers.add(u.getUsername());
            mappingOfUsers.put(u.getUsername(), u);

        }
        followingListOfLoggedinUser.retainAll(namesOfUsers);
        namesOfUsers.removeAll(followingListOfLoggedinUser);
        List<Users> followList = new LinkedList<Users>();
        List<Users> unfollowList =new LinkedList<Users>();
        for(String s:followingListOfLoggedinUser)
        {
            System.out.println(s);
            unfollowList.add(mappingOfUsers.get(s));
        }
        modelMap.addAttribute("length",unfollowList.size());
        System.out.println("here");
        for(String s:namesOfUsers)
        {
            System.out.println(s);
            unfollowList.add(mappingOfUsers.get(s));
        }
        String jsonString = new Gson().toJson(unfollowList);
        System.out.println(jsonString);
        return jsonString;
    }
}