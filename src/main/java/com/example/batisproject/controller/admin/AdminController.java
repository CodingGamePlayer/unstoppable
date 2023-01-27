package com.example.batisproject.controller.admin;

import com.example.batisproject.controller.AuthenticationForModel;
import com.example.batisproject.dto.PageRequestDTO;
import com.example.batisproject.dto.PageResponseDTO;
import com.example.batisproject.dto.UserDTO;
import com.example.batisproject.entity.User;
import com.example.batisproject.service.admin.impl.AdminServiceImpl;
import com.example.batisproject.service.user.impl.UserServiceImpl;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AuthenticationForModel authenticationForModel;
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AdminServiceImpl adminService;

    @GetMapping("/manage-user")
    public String main(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model) {

//        if(authenticationForModel.getAuthentication() == null){
//            return "redirect:/login";
//        }
        if (bindingResult.hasErrors())
            pageRequestDTO = PageRequestDTO.builder().build();

        PageResponseDTO<UserDTO> pageResponseDTO = adminService.selectAllForPaging(pageRequestDTO);
        User user = authenticationForModel.getAuthentication();


        model.addAttribute("userList1", pageResponseDTO);
        model.addAttribute("user", user.getNickname());

        return "admin/manage-user";
    }

    @GetMapping("/manage-user/change-role")
    public String changeRole(UserDTO userDTO, Model model) {


        UserDTO resultDTO = userService.existsByEmail(userDTO.getUsername());

        model.addAttribute("user",resultDTO);

        return "admin/for-function/change-role";
    }


}
