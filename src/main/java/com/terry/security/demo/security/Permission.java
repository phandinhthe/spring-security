package com.terry.security.demo.security;

public enum Permission {
    READ("read"),
    WRITE("write");

    private String permission;

    Permission(String permission) {
        this.permission = permission;
    }
}
