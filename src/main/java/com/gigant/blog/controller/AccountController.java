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

import java.util.Arrays;
import java.util.Map;

@Controller
@RequestMapping("/userpage")
@SessionAttributes("user")
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
    public String getUserPage(@SessionAttribute("user") Account currentAccount, Model model) {
        model.addAllAttributes(Map.of(
                "currentAccount",currentAccount,
                "postList",userPostService.getPostListByAccountId(currentAccount.getId())
        ));
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
            @SessionAttribute("user") Account currentAccount,
            @RequestParam("post-images") MultipartFile[] multipartFile
    ) {
        userPostService.addNewPost(userPost, currentAccount, multipartFile);
        return "redirect:/userpage";
    }
}
