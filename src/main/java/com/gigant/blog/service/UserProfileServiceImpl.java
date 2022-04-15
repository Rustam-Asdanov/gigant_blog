package com.gigant.blog.service;

import com.gigant.blog.model.UserProfile;
import com.gigant.blog.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService{

    private final UserProfileRepository userProfileRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserProfileServiceImpl(UserProfileRepository userProfileRepository, PasswordEncoder passwordEncoder) {
        this.userProfileRepository = userProfileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserProfile> getAllUserProfiles() {
        return null;
    }

    @Override
    public void saveUserProfile(UserProfile userProfile) {

    }

    @Override
    public void deleteUserProfileById(long id) {

    }

    @Override
    public UserProfile getUserProfileById(long id) {
        return null;
    }

    @Override
    public UserProfile getUserProfileByUsername(String username) {
        return null;
    }
}
