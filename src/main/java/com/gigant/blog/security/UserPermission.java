package com.gigant.blog.security;

public enum UserPermission {

    PAGE_READ("can read data in page"),
    PAGE_WRITE("can edit data in page"),
    ACCOUNT_READ("can read data in admin page"),
    ACCOUNT_WRITE("can edit data in admin page");

    private final String permissionInfo;

    UserPermission(String permissionInfo) {
        this.permissionInfo = permissionInfo;
    }

    public String getPermissionInfo() {
        return permissionInfo;
    }
}
