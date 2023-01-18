package com.example.batisproject.service.impl;

import com.example.batisproject.domain.Article;
import com.example.batisproject.dto.ArticleDto;
import com.example.batisproject.dto.PageRequestDTO;
import com.example.batisproject.dto.PageResponseDTO;
import com.example.batisproject.mapper.ArticleMapper;
import com.example.batisproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public PageResponseDTO<ArticleDto> selectAll(PageRequestDTO pageRequestDTO) {

        List<Article> articles = articleMapper.selectAll(pageRequestDTO);
        List<ArticleDto> dtoList = articles.stream()
                .map(article -> modelMapper.map(article, ArticleDto.class))
                .collect(Collectors.toList());

        int total = articleMapper.getCount();

        PageResponseDTO<ArticleDto> pageResponseDTO = PageResponseDTO.<ArticleDto>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();


        return pageResponseDTO;
    }

    @Override
    public ArticleDto selectOne(int id) {
        ArticleDto articleDto = modelMapper.map(articleMapper.selectOne(id), ArticleDto.class);

        return articleDto;
    }

    @Override
    public int createOne(ArticleDto articleDto) {

        if(articleDto == null) {
            throw new IllegalArgumentException("게시글 생성에 실패 하였습니다.");
        }

        Article article = modelMapper.map(articleDto, Article.class);
        int result = articleMapper.create(article);

        if(result<0){
            return 0;
        }

        return 1;
    }

    @Override
    public int updateOne(ArticleDto articleDto) {

        Article target = articleMapper.selectOne(articleDto.getId());

        if(target == null) {
            throw new IllegalArgumentException("수정하려고 하는 게시글이 존재 하지 않습니다.");
        }

        Article article = modelMapper.map(articleDto, Article.class);
        int result = articleMapper.update(article);

        if(result<0){
            return 0;
        }
        return 1;
    }

    @Override
    public int deleteOne(int id) {

        int result = articleMapper.delete(id);

        if(result<0){
            return 0;
        }
        return 1;
    }
}
