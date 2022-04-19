package com.gigant.blog.service;

import com.gigant.blog.model.Account;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AccountService {

    List<Account> getAllAccounts();

    void saveAccount(Account account, MultipartFile multipartFile);

    void deleteAccountById(long id);

    Account getAccountById(long id);

    Account getAccountByUsername(String username);
}
