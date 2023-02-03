package com.example.batisproject.yk;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.File;
import java.util.List;

import javax.mail.Multipart;

import com.example.batisproject.service.yk.Yk_locationService;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.example.batisproject.dto.CategoryDTO;
import com.example.batisproject.dto.LocationDTO;
import com.example.batisproject.entity.Category;
import com.example.batisproject.entity.FileInfo;
import com.example.batisproject.entity.Location;
import com.example.batisproject.mapper.Yk_fileInfoMapper;
import com.example.batisproject.mapper.jungi.CategoryMapper;
import com.example.batisproject.mapper.yk.Yk_categoryMapper;
import com.example.batisproject.mapper.yk.Yk_locationMapper;
import com.example.batisproject.service.yk.Yk_categoryService;
import com.example.batisproject.service.yk.Yk_file_info_Service;
import com.example.batisproject.service.yk.impl.Yk_categoryServiceImpl;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootTest
public class MapperTest {
    
    @Autowired
    Yk_categoryService categoryService;
    
        @Autowired
        Yk_categoryMapper categoryMapper;
        

    @Autowired
    Yk_locationMapper locationMapper;
    
    @Autowired
    Yk_locationService locationService;
    
    @Autowired
    Yk_fileInfoMapper fileInfoMapper;

    @Autowired
    Yk_file_info_Service gatherService;



     

    @Test
    void getList(){
        List<Location> list = locationMapper.getList();
        assertNotNull(list);
    }

    @Test
    void c_getList(){
        List<Category> list = categoryMapper.getList();
        System.out.println(list);
        assertNotNull(list);
        
    }

    
    
    @Test
    void serviceTest_d(){
        List<CategoryDTO>list = categoryService.getList();

        assertNotNull(list);
    }


    @Test
    void serviceTest_lo(){
        List<LocationDTO> list = locationService.getList();
        assertNotNull(list);
    }


    @Test
    void imgTest(){
        FileInfo file = FileInfo.builder()
            
            .fileName("test")
            .saveFileName("test-test")
            .contentType("png")
            .deleteFlag(false)
            .build();
        int result = fileInfoMapper.putImgInfo(file);
        log.info("------------------------------------------------result"+result);
    }


    // @Test
    // void serviceFileTest(){
    //     FileInfo file = FileInfo.builder()
            
    //     .fileName("test")
    //     .saveFileName("test-test")
    //     .contentType("png")
    //     .deleteFlag(false)
    //     .build();

        

    // }


}
