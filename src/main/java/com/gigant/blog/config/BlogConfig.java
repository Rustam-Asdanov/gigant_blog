package com.gigant.blog.config;

import com.gigant.blog.model.Account;
import com.gigant.blog.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Date;


@Configuration
public class BlogConfig {

    private final AccountService accountService;

    @Autowired
    public BlogConfig(AccountService accountService) {
        this.accountService = accountService;
    }


    @Bean
    public CommandLineRunner commandLineRunner(){
        return args -> {
            Account defaultAccount = new Account(
                    0,
                    "abc",
                    "abc",
                    "Ted",
                    "Ted",
                    "Baku",
                    "Azerbaijan",
                    "man",
                    LocalDate.of(1990,2,20),
                    "profile@gmail.com",
                    "profile.jpg");

            accountService.saveAccount(defaultAccount, null);

        };


    }

}
