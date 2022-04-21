package com.gigant.blog.repository;

import com.gigant.blog.model.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPostRepository extends JpaRepository<UserPost,Long> {
    List<UserPost> getAllUserPostByAccountId(long id);
}
