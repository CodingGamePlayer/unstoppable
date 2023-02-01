package com.example.batisproject.service.yk.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
// import org.modelmapper.ModelMapper;
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
    
    // private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<CategoryDTO> getList() {
        
        // List<Category>list= categoryMapper.getList();
        // list.stream()
        // .map(category->modelMapper.map(category, CategoryDTO.class))
        // .collect(Collectors.toList());
        // return null;
 
        return categoryMapper.getList().stream()
        .map(category-> modelMapper.map(category, CategoryDTO.class))
        .collect(Collectors.toList());

    }

    

}
