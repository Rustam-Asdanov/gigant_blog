package com.gigant.blog.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum UserRole {

    GUEST(Sets.newHashSet()),
    ADMIN(Sets.newHashSet());

    private final Set<UserPermission> userPermissions;

    UserRole(Set<UserPermission> userPermissions) {
        this.userPermissions = userPermissions;
    }

    public Set<UserPermission> getUserPermissions() {
        return userPermissions;
    }

    public Set<SimpleGrantedAuthority> getSimpleGrantedAuthorities(){

        return getUserPermissions().stream()
                .map(userPermission -> new SimpleGrantedAuthority(userPermission.getPermissionInfo()))
                .collect(Collectors.toSet());
    }
}
