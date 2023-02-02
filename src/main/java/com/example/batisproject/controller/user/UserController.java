package com.example.batisproject.controller.user;

import com.example.batisproject.annotaion.CurrentUser;
import com.example.batisproject.controller.AuthenticationForModel;
import com.example.batisproject.dto.UserDTO;
import com.example.batisproject.entity.User;
import com.example.batisproject.service.location.LocationService;
import com.example.batisproject.service.user.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private LocationService locationService;

    @GetMapping("/user/main")
    public String main(Model model, @CurrentUser User user) {

        UserDTO userDTO = userService.existsByEmail(user.getUsername());

        model.addAttribute("locationList",locationService.getAll());
        model.addAttribute("user", userDTO);


        return "user/main";
    }

    @GetMapping("/user/payment")
    public String payment(Model model, @CurrentUser User user) {

        UserDTO userDTO = userService.existsByEmail(user.getUsername());

        model.addAttribute("userDTO", userDTO);

        return "user/for-function/user-payment";
    }

    @GetMapping("/user/update/location")
    public String updateUserLocation(Integer location, @CurrentUser User user, Model model) {
        System.out.println("locaiton : " + location);
        UserDTO userDTO = userService.existsByEmail(user.getUsername());
        userDTO.setLocation(location);
        int result = userService.updateUserByLocation(userDTO);
        log.info("result : " + result);
        model.addAttribute("user", userDTO);
        return "user/main";
    }

}
