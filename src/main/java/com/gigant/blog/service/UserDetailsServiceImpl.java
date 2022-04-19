package com.gigant.blog.service;

import com.gigant.blog.model.Account;
import com.gigant.blog.model.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.gigant.blog.security.UserRole.GUEST;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDetailsServiceImpl(AccountService accountService, PasswordEncoder passwordEncoder) {
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserDetails(username);
    }

    private UserDetails getUserDetails(String username){

        Account theAccount = accountService.getAccountByUsername(username);

        UserDetailsImpl userDetails = new UserDetailsImpl(
                theAccount.getUsername(),
                theAccount.getPassword(),
                GUEST.getSimpleGrantedAuthorities(),
                true,
                true,
                true,
                true
        );

        return userDetails;
    }
}
