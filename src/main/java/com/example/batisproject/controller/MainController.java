package com.example.batisproject.controller;

import com.example.batisproject.dto.UserDTO;
import com.example.batisproject.entity.User;
import com.example.batisproject.service.user.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class MainController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/signup")
    public String signup() {
        return "sign-up";
    }

    @PostMapping("/signup")
    public String signup(UserDTO userDTO) {

        log.info("UserDTO : " + userDTO.toString());
        int result = userService.insert(userDTO);

        return "redirect:/user/main";
    }



    @GetMapping("/")
    public String main() {
        return "main_other";
    }
}
