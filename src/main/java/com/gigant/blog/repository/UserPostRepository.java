package com.gigant.blog.repository;

import com.gigant.blog.model.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPostRepository extends JpaRepository<UserPost,Long> {
}
