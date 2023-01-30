package com.example.batisproject.controller.yk;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class GatherController {
    
    @GetMapping("user/gather/register")
    public String test_t(){
        System.out.println("레즈스터 컨트롤 진입 성공");
        
        return "gather/register";
    }




    

}
