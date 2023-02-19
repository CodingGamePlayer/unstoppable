package com.example.batisproject.service.category;

import com.example.batisproject.dto.CategoryDTO;
import com.example.batisproject.dto.GatherDTO;
import com.example.batisproject.entity.Category;
import com.example.batisproject.mapper.jungi.CategoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService{

    private final CategoryMapper categoryMapper;

    private ModelMapper modelMapper = new ModelMapper();
    @Override
    public List<CategoryDTO> getAllMainCategory() {
        return categoryMapper.getAllMainCategory().stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }
}
