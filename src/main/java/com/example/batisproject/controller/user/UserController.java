package com.example.batisproject.controller.user;

import com.example.batisproject.dto.UserDTO;
import com.example.batisproject.service.user.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class UserController {

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




}
