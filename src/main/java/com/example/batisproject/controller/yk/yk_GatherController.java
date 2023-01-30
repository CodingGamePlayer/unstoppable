package com.example.batisproject.controller.yk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.batisproject.controller.AuthenticationForModel;
import com.example.batisproject.dto.UserDTO;
import com.example.batisproject.entity.User;
import com.example.batisproject.service.user.UserService;

@Controller
public class yk_GatherController {
    
    
    
    @Autowired
    private UserService userService;


    @GetMapping("/user/gather/register")
    public String regiser(Model model){
        User user = new AuthenticationForModel().getAuthentication();

        UserDTO userDTO = userService.existsByEmail(user.getUsername());

        model.addAttribute("user", userDTO);

        System.out.println("레즈스터 컨트롤 진입 성공");

        return "user/gather/register";
    }

}
