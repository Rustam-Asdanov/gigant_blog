package com.gigant.blog.controller;

import com.gigant.blog.model.Account;
import com.gigant.blog.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.Authenticator;

@Controller
@RequestMapping("/blog")
public class MainController {

    private final AccountService accountService;

    @Autowired
    public MainController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public String getMainPage(){
        return "main-page";
    }

    @PostMapping(
            path = "/save",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String saveUser(
            @ModelAttribute("user") Account account,
            @RequestParam("profileImage")MultipartFile multipartFile
            ){
        accountService.saveAccount(account, multipartFile);

        return "redirect:/blog";
    }

    @GetMapping("/userpage")
    public String getUserPage(Model model, Authentication authentication){
        String username = authentication.getName();
        Account theAccount = accountService.getAccountByUsername(username);
        model.addAttribute("account", theAccount);
        return "userpage";
    }
}
