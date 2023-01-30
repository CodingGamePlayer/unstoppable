package com.example.batisproject.controller.user;

import com.example.batisproject.controller.AuthenticationForModel;
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

@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/user/main")
    public String main(Model model) {


        User user = new AuthenticationForModel().getAuthentication();

        UserDTO userDTO = userService.existsByEmail(user.getUsername());

        model.addAttribute("user", userDTO);

        return "user/main";
    }

    @GetMapping("/user/payment")
    public String payment(Model model) {

        User user = new AuthenticationForModel().getAuthentication();
        UserDTO userDTO = userService.existsByEmail(user.getUsername());

        model.addAttribute("userDTO", userDTO);

        return "user/for-function/user-payment";
    }


}
