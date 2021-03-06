package com.gigant.blog.controller;

import com.gigant.blog.model.Account;
import com.gigant.blog.service.AccountService;
import com.gigant.blog.service.UserPostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/blog")
@SessionAttributes("user")
public class MainController {

    private String folder = "";
    private final AccountService accountService;
    private final UserPostService userPostService;

    @Autowired
    public MainController(AccountService accountService, UserPostService userPostService) {
        this.accountService = accountService;
        this.userPostService = userPostService;
    }

    @GetMapping
    public String getMainPage(Authentication authentication,
                              HttpSession httpSession,
                              SessionStatus sessionStatus,
                              Model model) {
        if (authentication != null) {
            httpSession.setAttribute("user", accountService.getAccountByUsername(authentication.getName()));
        }

        model.addAttribute("cardList",userPostService.getAllPosts());

        return folder + "main-page";
    }


    @PostMapping(
            path = "/save",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String saveUser(
            @ModelAttribute("user") Account account,
            @RequestParam("profileImage") MultipartFile multipartFile
    ) {
        accountService.saveAccount(account, multipartFile);
        return "redirect:/blog";
    }

}
