package com.gigant.blog.controller;

import com.gigant.blog.model.Account;
import com.gigant.blog.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/blog")
public class MainController {

    private String folder = "";
    private final AccountService accountService;

    @Autowired
    public MainController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public String getMainPage() {
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
