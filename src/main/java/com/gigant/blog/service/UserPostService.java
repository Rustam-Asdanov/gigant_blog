package com.gigant.blog.service;

import com.gigant.blog.model.Account;
import com.gigant.blog.model.UserPost;
import org.springframework.web.multipart.MultipartFile;

public interface UserPostService {
    void addNewPost(UserPost userPost, Account currentAccount, MultipartFile[] multipartFile);
}
