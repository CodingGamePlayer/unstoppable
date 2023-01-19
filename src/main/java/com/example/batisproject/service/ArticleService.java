package com.example.batisproject.service;

import com.example.batisproject.dto.ArticleDTO;
import com.example.batisproject.dto.PageRequestDTO;
import com.example.batisproject.dto.PageResponseDTO;

public interface ArticleService {

    public PageResponseDTO<ArticleDTO> selectAll(PageRequestDTO pageRequestDTO);

    public ArticleDTO selectOne(int id);

    public int createOne(ArticleDTO articleDto);

    public int updateOne(ArticleDTO articleDto);

    public int deleteOne(int id);

}
