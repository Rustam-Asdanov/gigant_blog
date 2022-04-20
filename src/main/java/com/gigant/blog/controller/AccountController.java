package com.gigant.blog.controller;

import com.gigant.blog.model.Account;
import com.gigant.blog.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@Controller
@RequestMapping("/userpage")
public class AccountController {

    private String folder = "userpage/";
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public String getUserPage(Model model, Authentication authentication) {
        String username = authentication.getName();
        Account theAccount = accountService.getAccountByUsername(username);
        model.addAttribute("account", theAccount);
        return folder + "userpage";
    }

    @GetMapping("/newPost")
    public String getNewPostPage(Model model) {

        return folder + "new-post";
    }

    @PostMapping(
            path = "/savePost"
    )
    public String saveNewPost(@RequestParam("post-images") MultipartFile[] multipartFile) {
        System.out.println(Arrays.toString(multipartFile));
        return "redirect:/userpage";
    }
}
