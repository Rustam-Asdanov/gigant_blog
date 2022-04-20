package com.gigant.blog.controller;

import com.gigant.blog.model.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    private String folder = "help/";

    @GetMapping
    public String getPage() {
        return folder + "welcome";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return folder + "login";
    }

    @GetMapping("/signup")
    public String getSignUpPage(Model model) {
        model.addAttribute("user", new Account());
        return folder + "signup";
    }

    @GetMapping("/building")
    public String getBuildingPage() {
        return folder + "building";
    }
}
