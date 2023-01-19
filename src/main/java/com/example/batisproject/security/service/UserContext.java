package com.example.batisproject.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserContext extends User {

    private final com.example.batisproject.domain.User User;

    public UserContext(com.example.batisproject.domain.User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getNickname(), user.getPassword(), authorities);

        this.User = user;
    }

    public com.example.batisproject.domain.User getUser() {
        return User;
    }
}
