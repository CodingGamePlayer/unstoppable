package com.example.batisproject.controller.taehyun;

import com.example.batisproject.controller.AuthenticationForModel;
import com.example.batisproject.dto.UserDTO;
import com.example.batisproject.entity.Location;
import com.example.batisproject.entity.User;
import com.example.batisproject.service.user.UserService;
import com.example.batisproject.service.user.taehyun.MemberUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class MemberUpdateController {



    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationForModel authenticationForModel;
    private MemberUpdateService memberUpdateService;

    @GetMapping("/user/profile")
    public String updateForm(Model model) {

        User user = new AuthenticationForModel().getAuthentication();   //이걸 선언해서 user라는 변수에 user 정보를 담음

        UserDTO dto = userService.existsByEmail(user.getUsername());    // user에 있는 username이 필요 user변수에 저장된 username을 가져옴  => user.getUsarname()
        // 아까는  new AuthenticationForModel().getAuthentication() 요걸 안해서 user 정보가 없음

        model.addAttribute("user", dto);
           // model에 user란 이름으로 담았으니까 dto 변수에 들어간 데이터들이 뷰로 전달댐

        // 여기에 담아야함
        // sido 조회하는 SQL 은 있어? --> ?? 만들어야 될거같아 컨트롤러만일단 먼저 만들고 컨트롤러 ->서비스 -> 맵퍼 이렇게 처리할거임
        // 컨트롤러가 여기가 컨트롤러인데 멀 만들어 서비스부터 만들면 돼  테이블만 줘바  sido

        List<Location> sidoList = userService.selectSidoList();

        model.addAttribute("sidoList", sidoList);

           return "user/mypage/profile";

    }




    }