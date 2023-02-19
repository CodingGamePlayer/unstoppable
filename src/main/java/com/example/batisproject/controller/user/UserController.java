package com.example.batisproject.controller.user;

import com.example.batisproject.annotaion.CurrentUser;

import com.example.batisproject.dto.UserDTO;
import com.example.batisproject.entity.User;
import com.example.batisproject.service.location.LocationService;
import com.example.batisproject.service.user.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//

@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private LocationService locationService;

    @Autowired
    private ModelMapper modelMapper;

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
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userDTO.setLocation(location);
        userService.updateUserByLocation(userDTO);
        model.addAttribute("user", userDTO);
        return "redirect:/user/main";
    }
    @GetMapping("/user/myListLocation/update")
    public String updateUserListLocation(Integer location, @CurrentUser User user, Model model) {

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userDTO.setLocation(location);
        userService.updateUserByLocation(userDTO);
        model.addAttribute("user", userDTO);
        return "redirect:/user/myGather";
    }

    @GetMapping("/user/listLocation/update")
    public String updateUserMyListLocation(Integer location, @CurrentUser User user, Model model) {

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userDTO.setLocation(location);
        userService.updateUserByLocation(userDTO);
        model.addAttribute("user", userDTO);
        return "redirect:/user/gather";
    }

}
