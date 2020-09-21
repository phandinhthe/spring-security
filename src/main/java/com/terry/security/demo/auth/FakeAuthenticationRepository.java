package com.terry.security.demo.auth;

import com.terry.security.demo.security.AuthorityHelper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import static com.terry.security.demo.security.Role.ADMIN;
import static com.terry.security.demo.security.Role.USER;

@Component
public class FakeAuthenticationRepository implements AuthenticationRepository {
    private final PasswordEncoder passwordEncoder;

    public FakeAuthenticationRepository(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UserDetails loadByUsername(String name) {
        List<UserDetails> userDetails = List.of(
                new ApplicationUser(AuthorityHelper.authoritiesFrom(USER), passwordEncoder.encode("password")
                        , "user", true, true, true, true)
                , new ApplicationUser(AuthorityHelper.authoritiesFrom(ADMIN), passwordEncoder.encode("password123")
                        , "admin", true, true, true, true));

        Predicate<UserDetails> predicateUser = (user) -> name.equals(user.getUsername());
        return userDetails.stream().filter(predicateUser).findFirst().get();
    }

}
