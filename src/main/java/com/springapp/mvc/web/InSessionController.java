package com.springapp.mvc.web;

import com.google.gson.Gson;
import com.springapp.mvc.model.Users;
import com.springapp.mvc.service.DbRepositoryService;
import com.springapp.mvc.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/")
public class InSessionController {

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "hello";
	}

    private final DbRepositoryService dbRepositoryService;

    @Autowired
    public InSessionController(DbRepositoryService dbRepositoryService) {
        this.dbRepositoryService = dbRepositoryService;
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String fetchUsersTweets(@PathVariable("username") String username, ModelMap modelMap) {
        List<Tweet> tweets= dbRepositoryService.fetchUsersTweets(username);

        modelMap.addAttribute("list", tweets);
        modelMap.addAttribute("username", username);


        for(Tweet t:tweets){
            //System.out.println(t.getTweetid());
        }

        String jsonString = new Gson().toJson(tweets);
        System.out.println(jsonString);
        return "showTweets";
    }

    @RequestMapping(value = "/{username}/newTweets", method = RequestMethod.GET)
    @ResponseBody
    public String fetchNewUsersTweets(@PathVariable("username") String username, ModelMap modelMap) {
        List<Tweet> tweets= dbRepositoryService.fetchUsersTweets(username);

        modelMap.addAttribute("list", tweets);
        modelMap.addAttribute("username", username);



        String jsonString = new Gson().toJson(tweets);
       // System.out.println(jsonString);
        return jsonString;
    }


    @RequestMapping(value = "/{username}/Following", method = RequestMethod.GET)
   // @ResponseBody
    public String fetchFollowingList(@PathVariable("username") String username, ModelMap modelMap) {
        List<String> users= dbRepositoryService.findFollowingList(username);

        for(String s:users)
        System.out.println(s);
        modelMap.addAttribute("list", users);
        modelMap.addAttribute("username", username);
        return "following";
    }


    @RequestMapping(value = "/{username}/Followers", method = RequestMethod.GET)
    // @ResponseBody
    public String fetchFollowersList(@PathVariable("username") String username, ModelMap modelMap) {
        List<String> users= dbRepositoryService.findFollowersList(username);

        for(String s:users)
            System.out.println(s);
        modelMap.addAttribute("list", users);
        modelMap.addAttribute("username", username);
        return "followers";
    }


}