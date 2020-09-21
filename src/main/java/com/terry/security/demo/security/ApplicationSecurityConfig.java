package com.terry.security.demo.security;

import com.terry.security.demo.jwt.JwtTokenVerifier;
import com.terry.security.demo.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import static com.terry.security.demo.security.Permission.WRITE;
import static com.terry.security.demo.security.Role.ADMIN;
import static com.terry.security.demo.security.Role.USER;

@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager()))
                .addFilterAfter(new JwtTokenVerifier(), JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(antPatterns()).permitAll()
                .antMatchers("/api/**").hasRole(USER.name())
                .antMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(WRITE.name())
                .antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(WRITE.name())
                .antMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority(WRITE.name())
                .antMatchers(HttpMethod.GET, "/management/api/**").hasAnyRole(ADMIN.name(), USER.name())
                .anyRequest().authenticated();
    }

    private String[] antPatterns() {
        String[] patterns = {"/"};
        return patterns;
    }
}
