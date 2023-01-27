package com.example.batisproject.controller;

import com.example.batisproject.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationForModel {

    // 해당 메소드는 사용자 정보를 들고 오기 위함으로 만들어진 메소드임.
//    테스트용 글임
    public User getAuthentication() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            return(User) authentication.getPrincipal();
        }

        return null;
    }
}
