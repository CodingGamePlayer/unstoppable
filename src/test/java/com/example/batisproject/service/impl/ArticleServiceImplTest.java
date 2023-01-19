package com.example.batisproject.service.impl;

import com.example.batisproject.dto.ArticleDTO;
import com.example.batisproject.dto.PageRequestDTO;
import com.example.batisproject.dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
class ArticleServiceImplTest {

    @Autowired
    private ArticleServiceImpl articleService;

    @Test
    void selectAll() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<ArticleDTO> pageResponseDTO = articleService.selectAll(pageRequestDTO);

       log.info(pageResponseDTO);

       pageResponseDTO.getDtoList().stream()
               .forEach(articleDto -> log.info(articleDto));

    }
}