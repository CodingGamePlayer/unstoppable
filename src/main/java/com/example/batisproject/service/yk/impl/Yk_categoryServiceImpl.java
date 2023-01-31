package com.example.batisproject.service.yk.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.batisproject.dto.CategoryDTO;
import com.example.batisproject.service.yk.Yk_categoryService;

@Service
public class Yk_categoryServiceImpl implements Yk_categoryService {
    
    @Autowired
    Yk_categoryServiceImpl categoryService;

    @Override
    public List<CategoryDTO> getList() {
        
        return null;
    }

    

}
