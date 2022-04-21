package com.gigant.blog.config;

import com.gigant.blog.model.Account;
import com.gigant.blog.service.AccountService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
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

            File file = new File("src/main/resources/static/userdata");
            FileUtils.deleteDirectory(file);

            file.mkdir();

            Account defaultAccount = new Account(
                    0,
                    "abc",
                    "abc",
                    "Ted",
                    "Ted",
                    "Baku",
                    "Azerbaijan",
                    "man",
                    null,
                    "profile@gmail.com",
                    "profile.jpg",null);

            accountService.saveAccount(defaultAccount, null);

        };




    }

}
