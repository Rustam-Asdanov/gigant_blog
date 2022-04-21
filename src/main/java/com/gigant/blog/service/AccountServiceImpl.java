package com.gigant.blog.service;

import com.gigant.blog.model.Account;
import com.gigant.blog.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public void saveAccount(Account account, MultipartFile multipartFile) {

        String fileName;
        fileName = savingImage(account, multipartFile);

        account.setProfileImageLink(fileName);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
    }

    /**
     * in this method we get current account data and its profile photo
     * we crate new folder for user, save its profile image and return new
     * generated name. But if our user don't upload image, we just create
     * folder for him and return default image name
     * @param account
     * @param multipartFile
     * @return
     */
    private String savingImage(Account account, MultipartFile multipartFile) {

        File file = new File("src/main/resources/static/userdata/user_"+ (accountRepository.findAll().size()+1));
        if (!file.exists()){
            file.mkdir();
        }

        String fileName;
        if(multipartFile == null) {
            return "profile.jpg";
        }

        fileName = String.format("%s-%s", multipartFile.getOriginalFilename(), UUID.randomUUID());

        Path path = Paths.get(file.getPath(),"/"+fileName);

        try {
            multipartFile.transferTo(path);
        } catch (IOException e) {
            throw new IllegalStateException("We can not store your file",e);
        }
        return fileName;
    }

    @Override
    public void deleteAccountById(long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Account getAccountById(long id) {

        Account account = accountRepository.getAccountById(id);

        if(account == null){
            throw new IllegalStateException(String.format("User with id %d not found",id));
        }

        return account;
    }

    @Override
    public Account getAccountByUsername(String username) {
        Account account = accountRepository.getAccountByUsername(username);
        log.info("username is {}", account.getId());
        if(account == null){
            throw new IllegalStateException(String.format("User with id %s not found",username));
        }

        return account;
    }
}
