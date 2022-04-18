package com.gigant.blog.controller;

import com.gigant.blog.model.UserProfile;
import com.gigant.blog.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/blog")
public class MainController {

    private final UserProfileService userProfileService;

    @Autowired
    public MainController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping
    public String getMainPage(){
        return "main-page";
    }

    @GetMapping("/signup")
    public String getSignUpPage(Model model){
        model.addAttribute("user", new UserProfile());
        return "signup";
    }

    @PostMapping(
            path = "/save",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String saveUser(
            @ModelAttribute("user") UserProfile userProfile,
            @RequestParam("profileImage")MultipartFile multipartFile
            ){
        userProfileService.saveUserProfile(userProfile, multipartFile);

        return "redirect:/login";
    }
}
