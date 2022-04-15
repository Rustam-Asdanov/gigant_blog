package com.gigant.blog.service;

import com.gigant.blog.model.UserProfile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserProfileService {

    List<UserProfile> getAllUserProfiles();

    void saveUserProfile(UserProfile userProfile, MultipartFile multipartFile);

    void deleteUserProfileById(long id);

    UserProfile getUserProfileById(long id);

    UserProfile getUserProfileByUsername(String username);
}
