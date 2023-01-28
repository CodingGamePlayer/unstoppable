package com.example.batisproject.controller.admin;

import com.example.batisproject.controller.AuthenticationForModel;
import com.example.batisproject.dto.GatherDTO;
import com.example.batisproject.dto.PageRequestDTO;
import com.example.batisproject.dto.PageResponseDTO;
import com.example.batisproject.dto.UserDTO;
import com.example.batisproject.entity.User;
import com.example.batisproject.service.admin.impl.AdminServiceImpl;
import com.example.batisproject.service.user.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String showUserPage(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model) {

        PageRequestDTO revisedPageDTO = setKeyword(pageRequestDTO, bindingResult);

        PageResponseDTO<UserDTO> pageResponseDTO = adminService.searchUser(revisedPageDTO);
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

    @GetMapping("manage-gather")
    public String showGatherPage(@Valid PageRequestDTO pageRequestDTO,
                                 BindingResult bindingResult, Model model){

        PageRequestDTO revisedPageDTO = setKeyword(pageRequestDTO, bindingResult);

        PageResponseDTO<GatherDTO> pageResponseDTO = adminService.searchGather(revisedPageDTO);
        User user = authenticationForModel.getAuthentication();

        model.addAttribute("gatherList", pageResponseDTO);
        model.addAttribute("user", user.getNickname());

        return "admin/manage-gather";
    }



    PageRequestDTO setKeyword(PageRequestDTO pageRequestDTO, BindingResult bindingResult) {

        if(pageRequestDTO.getKeyword() != null)
            pageRequestDTO.setKeyword(pageRequestDTO.getKeyword());

        if (bindingResult.hasErrors()){

            pageRequestDTO = PageRequestDTO.builder().build();
        }

        return pageRequestDTO;
    }

}
