package com.example.batisproject.controller.yk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.batisproject.controller.AuthenticationForModel;
import com.example.batisproject.dto.UserDTO;
import com.example.batisproject.entity.User;
import com.example.batisproject.service.user.UserService;

@Controller
public class Yk_CommentController {
    
    @Autowired
    UserService userService;


    @GetMapping("/user/gather/detail/{g_id}/commentAdmin")
    public String commentAdmin(@PathVariable("g_id")Long g_id, Model model){
        System.out.println("코멘트관리 컨트롤");
         //유저이름 실어보내기 세션막아놔서 이렇게 세션 대체임
         User user = new AuthenticationForModel().getAuthentication();
         UserDTO userDTO = userService.existsByEmail(user.getUsername());
         model.addAttribute("user", userDTO);

        return "comment/commentAdmin";
    }
    
}
