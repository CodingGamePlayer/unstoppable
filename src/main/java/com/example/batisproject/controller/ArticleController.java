package com.example.batisproject.controller;

import com.example.batisproject.dto.ArticleDTO;
import com.example.batisproject.dto.PageRequestDTO;
import com.example.batisproject.service.impl.ArticleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;

@Controller
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleServiceImpl articleService;

    @GetMapping("/articles")
    public String articles(@Valid PageRequestDTO pageRequestDTO,
                           BindingResult bindingResult, Model model) {

        log.info(String.valueOf(pageRequestDTO));
        if(bindingResult.hasErrors())
            pageRequestDTO = PageRequestDTO.builder().build();

        model.addAttribute("articles", articleService.selectAll(pageRequestDTO));


        return "user/article/articles";
    }

    @GetMapping({"/article/{id}/{pagetype}", "/article/{id}/{pagetype}"})
    public String detail(@PathVariable("id") int id, @PathVariable("pagetype") String page, Model model){

        ArticleDTO articleDto = articleService.selectOne(id);

        model.addAttribute("article", articleDto);
        model.addAttribute("pageType", page);

        return "user/article/detail";
    }


    @GetMapping("/articles/insertform")
    public String insertForm(){

        return "user/article/newform";
    }
}
