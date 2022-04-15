package com.gigant.blog.repository;

import com.gigant.blog.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    UserProfile getUserProfileById(long id);

    UserProfile getUserProfileByUsername(String username);
}
