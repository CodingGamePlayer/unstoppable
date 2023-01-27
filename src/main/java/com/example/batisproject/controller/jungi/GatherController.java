package com.example.batisproject.controller.jungi;

import com.example.batisproject.controller.AuthenticationForModel;
import com.example.batisproject.entity.Category;
import com.example.batisproject.entity.Gather;
import com.example.batisproject.entity.User;
import com.example.batisproject.service.category.CategoryService;
import com.example.batisproject.service.gather.GatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class GatherController {

    private final GatherService gatherService;
    private final CategoryService categoryService;
    private final AuthenticationForModel authenticationForModel;

    @GetMapping("/user/gather")
    public String gatherList(@RequestParam(value = "category", required = false, defaultValue = "")Integer category, Model model) {

        List<Category> categoryList = categoryService.getAllMainCategory();
        List<Gather> gatherList;

        if(category == null) {
            gatherList = gatherService.getAll();
        } else{
            gatherList = gatherService.getByCategory(category);
        }

        model.addAttribute("gatherList", gatherList);
        model.addAttribute("categoryList", categoryList);
        return "gather/gatherList";
    }
    @GetMapping("/user/myGather")
    public String myGatherList(@RequestParam(value = "category", required = false, defaultValue = "")Integer category, Model model) {
        User user = authenticationForModel.getAuthentication();
        List<Category> categoryList = categoryService.getAllMainCategory();
        List<Gather> gatherList;

        if(category == null) {
            gatherList = gatherService.getAllByNickname(user.getNickname());
        } else{
            gatherList = gatherService.getByCategoryAndNickName(category, user.getNickname());
        }

        model.addAttribute("gatherList", gatherList);
        model.addAttribute("categoryList", categoryList);
        return "gather/myGatherList";
    }

    @Autowired
    public GatherController(GatherService gatherService, CategoryService categoryService, AuthenticationForModel authenticationForModel) {
        this.gatherService = gatherService;
        this.categoryService = categoryService;
        this.authenticationForModel = authenticationForModel;
    }
}
