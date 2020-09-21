package com.terry.security.demo.auth;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationRepository {
    UserDetails loadByUsername(String name);
}
