package com.example.batisproject.service.yk.impl;

import java.util.List;


// import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.batisproject.dto.CategoryDTO;

import com.example.batisproject.mapper.yk.Yk_categoryMapper;
import com.example.batisproject.service.yk.Yk_categoryService;

@Service
public class Yk_categoryServiceImpl implements Yk_categoryService {
    
    @Autowired
    Yk_categoryMapper categoryMapper;

    // @Autowired
    // ModelMapper modelMapper;

    @Override
    public List<CategoryDTO> getList() {
        
        

        // return categoryMapper.getList().stream()
        // .map(category-> modelMapper.map(category, CategoryDTO.class))
        // .collect(Collectors.toList());

        return null;
    }

    

}
