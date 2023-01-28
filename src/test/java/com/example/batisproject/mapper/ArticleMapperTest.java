package com.example.batisproject.mapper;

import com.example.batisproject.entity.Article;
import com.example.batisproject.dto.PageRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Slf4j
class ArticleMapperTest {

    @Autowired
    private ArticleMapper articleMapper;


    @Test
    void create() {

        Article article = Article.builder()
                .title("테스트")
                .body("테스트")
                .userid("테스트")
                .regdate(LocalDate.now())
                .moddate(LocalDate.now())
                .build();

        int result = articleMapper.create(article);
        log.info("article : " + article.toString());
        log.info("result : " + result);

    }

    @Test
    void selectAll() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .keyword("다른내용")
                .build();

        List<Article> articles = articleMapper.selectAll(pageRequestDTO);

        articles.forEach(article -> log.info(String.valueOf(article)));
    }

    @Test
    void update() {

        Article article = Article.builder()
                .id(3)
                .title("수정됨")
                .body("수정됨")
                .moddate(LocalDate.now())
                .userid("수정됨")
                .build();

        int updated = articleMapper.update(article);
        if(updated >0)
            log.info("성공적");
        else
            log.info("실패적");

    }

    @Test
    void delete() {

        int deleted = articleMapper.delete(4);
        if(deleted >0)
            log.info("성공적");
        else
            log.info("실패적");
    }

    @Test
    void selectOne() {

        Article article = articleMapper.selectOne(3);
        log.info(String.valueOf(article));
    }
}