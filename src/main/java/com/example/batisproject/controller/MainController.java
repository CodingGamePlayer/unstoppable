package com.example.batisproject.controller;

import com.example.batisproject.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class MainController {

    @GetMapping("/user/main")
    public String main(Model model) {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();

            model.addAttribute("user", user.getNickname());
        }


        return "main";
    }

    @GetMapping("/")
    public String main() {
        return "main_other";
    }
}
