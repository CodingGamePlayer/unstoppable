package com.example.batisproject.apicontroller;

import com.example.batisproject.controller.AuthenticationForModel;
import com.example.batisproject.dto.ArticleDTO;
import com.example.batisproject.dto.GatherDTO;
import com.example.batisproject.entity.User;
import com.example.batisproject.service.gather.GatherService;
import com.example.batisproject.service.impl.ArticleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class ArticleApiController {


    private ArticleServiceImpl articleService;

    @PostMapping("/api/article/create")
    public ResponseEntity<Integer> create(@RequestBody ArticleDTO articleDto){

        articleDto.setRegdate(LocalDate.now());
        int result = articleService.createOne(articleDto);

        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

    @PutMapping("/api/article/update")
    public ResponseEntity<Integer> update(@RequestBody ArticleDTO articleDto){

        log.info(articleDto.toString());

        articleDto.setModdate(LocalDate.now());
        int result = articleService.updateOne(articleDto);

        if(result == 0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }





    @Autowired
    public ArticleApiController(ArticleServiceImpl articleService) {
        this.articleService = articleService;
    }
}
