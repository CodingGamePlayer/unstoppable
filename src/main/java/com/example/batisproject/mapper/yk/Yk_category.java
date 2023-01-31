package com.example.batisproject.mapper.yk;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.batisproject.entity.Category;

@Mapper
public interface Yk_category {
    
    @Select("select*from category;")
    List<Category> getList();

}
