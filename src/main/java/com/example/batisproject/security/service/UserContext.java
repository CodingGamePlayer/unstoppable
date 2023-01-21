package com.example.batisproject.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserContext extends User {

    private final com.example.batisproject.entity.User User;

    public UserContext(com.example.batisproject.entity.User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getNickname(), user.getPassword(), authorities);

        this.User = user;
    }

    public com.example.batisproject.entity.User getUser() {
        return User;
    }
}
