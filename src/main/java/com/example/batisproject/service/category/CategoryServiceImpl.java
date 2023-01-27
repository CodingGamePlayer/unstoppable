package com.example.batisproject.service.category;

import com.example.batisproject.entity.Category;
import com.example.batisproject.mapper.jungi.CategoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService{

    private final CategoryMapper categoryMapper;
    @Override
    public List<Category> getAllMainCategory() {
        return categoryMapper.getAllMainCategory();
    }

    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }
}
