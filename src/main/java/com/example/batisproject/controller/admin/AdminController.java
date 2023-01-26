package com.example.batisproject.controller.admin;

import com.example.batisproject.controller.AuthenticationForModel;
import com.example.batisproject.dto.PageRequestDTO;
import com.example.batisproject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AuthenticationForModel authenticationForModel;

            @GetMapping("/manage-user")
        public String main(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model) {

            Authentication authentication = authenticationForModel.getAuthentication();

            if (authentication.isAuthenticated()) {
                User user = (User) authentication.getPrincipal();
                model.addAttribute("user", user.getNickname());
            }

            if(bindingResult.hasErrors())
            pageRequestDTO = PageRequestDTO.builder().build();

        return "admin/manage-user";
    }


}
