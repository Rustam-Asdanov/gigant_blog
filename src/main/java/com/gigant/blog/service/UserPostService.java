package com.gigant.blog.service;

import com.gigant.blog.model.Account;
import com.gigant.blog.model.UserPost;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserPostService {
    void addNewPost(UserPost userPost, Account currentAccount, MultipartFile[] multipartFile);

    List<UserPost> getPostListByAccountId(long id);
}
