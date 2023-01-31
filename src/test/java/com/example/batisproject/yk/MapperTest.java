package com.example.batisproject.yk;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.batisproject.entity.Category;
import com.example.batisproject.entity.Location;
import com.example.batisproject.mapper.jungi.CategoryMapper;
import com.example.batisproject.mapper.yk.Yk_categoryMapper;
import com.example.batisproject.mapper.yk.Yk_locationMapper;



@SpringBootTest
public class MapperTest {
    
    @Autowired
    Yk_locationMapper locationMapper;

    @Autowired
    Yk_categoryMapper categoryMapper;
    
    @Test
    void getList(){
        List<Location> list = locationMapper.getList();
        assertNotNull(list);
    }

    @Test
    void c_getList(){
        List<Category> list = categoryMapper.getList();

        assertNotNull(list);

    }

    


}
