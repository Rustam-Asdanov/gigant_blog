package com.gigant.blog.service;

import com.gigant.blog.model.UserDetailsImpl;
import com.gigant.blog.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.gigant.blog.security.UserRole.GUEST;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserProfileService userProfileService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDetailsServiceImpl(UserProfileService userProfileService, PasswordEncoder passwordEncoder) {
        this.userProfileService = userProfileService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserDetailsByUsername(username);
    }

    private UserDetails getUserDetailsByUsername(String username){

        UserProfile userProfile = userProfileService.getUserProfileByUsername(username);

        UserDetailsImpl userDetailsImpl = new UserDetailsImpl(
                userProfile.getUsername(),
                passwordEncoder.encode(userProfile.getPassword()),
                GUEST.getSimpleGrantedAuthorities(),
                true,
                true,
                true,
                true
        );

        return userDetailsImpl;

    }
}
