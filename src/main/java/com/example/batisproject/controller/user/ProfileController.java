package com.example.batisproject.controller.user;

import com.example.batisproject.annotaion.CurrentUser;
import com.example.batisproject.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;

    @GetMapping("/user/profile")
    public String show(@CurrentUser User user, Model model) {


        return "user/mypage/profile";
    }
}
