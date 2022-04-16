package com.gigant.blog.service;

import com.gigant.blog.model.UserProfile;
import com.gigant.blog.repository.UserProfileRepository;
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
        return userProfileRepository.findAll();
    }

    @Override
    public void saveUserProfile(UserProfile userProfile, MultipartFile multipartFile) {
        String fileName = String.format("%s-%s",multipartFile.getOriginalFilename(), UUID.randomUUID());

        File file = new File("src/main/resources/static/userdata/user_"+userProfile.getId());
        if (!file.exists()){
            file.mkdir();
        }

        Path path = Paths.get(file.getPath(),"/"+fileName);

        try {
            multipartFile.transferTo(path);
        } catch (IOException e) {
            throw new IllegalStateException("We can not store your file",e);
        }
        userProfile.setProfileImageLink(fileName);
        userProfile.setPassword(passwordEncoder.encode(userProfile.getPassword()));
        userProfileRepository.save(userProfile);
    }

    @Override
    public void deleteUserProfileById(long id) {
        userProfileRepository.deleteById(id);
    }

    @Override
    public UserProfile getUserProfileById(long id) {

        UserProfile userProfile = userProfileRepository.getUserProfileById(id);

        if(userProfile == null){
            throw new IllegalStateException(String.format("User with id %d not found",id));
        }

        return userProfile;
    }

    @Override
    public UserProfile getUserProfileByUsername(String username) {
        UserProfile userProfile = userProfileRepository.getUserProfileByUsername(username);

        if(userProfile == null){
            throw new IllegalStateException(String.format("User with id %s not found",username));
        }

        return userProfile;
    }
}
