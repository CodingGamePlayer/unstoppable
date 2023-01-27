package com.example.batisproject.controller.jungi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GatherController {

    @GetMapping("/user/gather")
    public String gatherList() {
        return "gatherList";
    }
}
