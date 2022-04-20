package com.gigant.blog.controller;

import com.gigant.blog.model.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @GetMapping
    public String getPage(){
        return "welcome";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/signup")
    public String getSignUpPage(Model model){
        model.addAttribute("user", new Account());
        return "signup";
    }

    @GetMapping("/building")
    public String getBuildingPage(){
        return "building";
    }
}
