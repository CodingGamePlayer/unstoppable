package com.example.batisproject.service.yk.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.example.batisproject.dto.CategoryDTO;
import com.example.batisproject.entity.Category;
import com.example.batisproject.mapper.yk.Yk_categoryMapper;
import com.example.batisproject.service.yk.Yk_categoryService;

@Configuration
@Service
public class Yk_categoryServiceImpl implements Yk_categoryService {
    
    @Autowired
    private Yk_categoryMapper categoryMapper;

    @Autowired
    private ModelMapper modelMapper;
    
    


    //카테고리 리스트 불러오기
    @Override
    public List<CategoryDTO> getList() {
        
        // List<Category>listEntity= categoryMapper.getList();
        

        // List<CategoryDTO> listDTO = listEntity.stream()
        // .map(category->modelMapper.map(category, CategoryDTO.class))
        // .collect(Collectors.toList());

        
        // return listDTO;
 
        return categoryMapper.getList().stream()
        .map(category-> modelMapper.map(category, CategoryDTO.class))
        .collect(Collectors.toList());

    }




    @Override
    public Long CategoryId(String detailName) {
        
        return categoryMapper.CategoryId(detailName);
    }

    

}
