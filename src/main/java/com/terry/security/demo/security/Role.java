package com.terry.security.demo.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.terry.security.demo.security.Permission.*;

public enum Role {
    USER(Sets.newHashSet(READ)),
    ADMIN(Sets.newHashSet(WRITE, READ));

    private final Set<Permission> permissionSet;

    Role(Set<Permission> permissionSet) {
        this.permissionSet = permissionSet;
    }

    public Set<Permission> getPermissionSet() {
        return this.permissionSet;
    }
}
