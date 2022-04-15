package com.gigant.blog.service;

import com.gigant.blog.model.UserProfile;

import java.util.List;

public interface UserProfileService {

    List<UserProfile> getAllUserProfiles();

    void saveUserProfile(UserProfile userProfile);

    void deleteUserProfileById(long id);

    UserProfile getUserProfileById(long id);

    UserProfile getUserProfileByUsername(String username);
}
