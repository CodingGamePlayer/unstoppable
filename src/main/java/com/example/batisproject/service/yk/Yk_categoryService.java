package com.example.batisproject.service.yk;

import java.util.List;

import com.example.batisproject.dto.CategoryDTO;


public interface Yk_categoryService {

    public List<CategoryDTO> getList();

    //대분류 이름으로 카테고리 아이디 받아오기
    Long CategoryId(String detailName);

    //카데고리 이름 받아오기
    String getCategoryName(Long c_id);
}
