package com.example.batisproject.service;

import com.example.batisproject.domain.Article;
import com.example.batisproject.dto.ArticleDto;
import com.example.batisproject.dto.PageRequestDTO;
import com.example.batisproject.dto.PageResponseDTO;

import java.util.List;

public interface ArticleService {

    public PageResponseDTO<ArticleDto> selectAll(PageRequestDTO pageRequestDTO);

    public ArticleDto selectOne(int id);

    public int createOne(ArticleDto articleDto);

    public int updateOne(ArticleDto articleDto);

    public int deleteOne(int id);

}
