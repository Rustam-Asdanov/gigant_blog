package com.gigant.blog.controller;

import com.gigant.blog.model.Account;
import com.gigant.blog.model.UserPost;
import com.gigant.blog.service.AccountService;
import com.gigant.blog.service.UserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/userpage")
public class AccountController {

    private String folder = "userpage/";
    private final AccountService accountService;
    private final UserPostService userPostService;

    @Autowired
    public AccountController(AccountService accountService, UserPostService userPostService) {
        this.accountService = accountService;
        this.userPostService = userPostService;
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
        model.addAttribute("userPost", new UserPost());
        return folder + "new-post";
    }

    @PostMapping(
            path = "/savePost",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String saveNewPost(
            @ModelAttribute("userPost") UserPost userPost,
            @RequestParam("post-images") MultipartFile[] multipartFile
    ) {
        userPostService.addNewPost(userPost, multipartFile);
        return "redirect:/userpage";
    }
}
