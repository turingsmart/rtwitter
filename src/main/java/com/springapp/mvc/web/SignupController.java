package com.springapp.mvc.web;

import com.springapp.mvc.model.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created with IntelliJ IDEA.
 * Users: maverick
 * Date: 15/7/13
 * Time: 5:24 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@SessionAttributes
public class SignupController {

    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public String showNewUserForm(Model m){

        m.addAttribute("users", (new Users()));
        return "signup";
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String submitForm(@Valid @ModelAttribute("users") Users users, BindingResult result, Model m){
        if(result.hasErrors())
            return "signup";

        m.addAttribute("message", "added user successfully");
        return "signup";
    }
}
