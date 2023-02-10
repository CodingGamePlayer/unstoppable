package com.example.batisproject.yk;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.Multipart;

import com.example.batisproject.service.yk.Yk_locationService;
import com.example.batisproject.service.yk.Yk_userSevice;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.example.batisproject.controller.jungi.GatherController;
import com.example.batisproject.controller.yk.yk_GatherController;
import com.example.batisproject.dto.CategoryDTO;
import com.example.batisproject.dto.FileInfoDTO;
import com.example.batisproject.dto.GatherCommentDTO;
import com.example.batisproject.dto.GatherDTO;
import com.example.batisproject.dto.LocationDTO;
import com.example.batisproject.dto.UserDTO;
import com.example.batisproject.entity.Category;
import com.example.batisproject.entity.FileInfo;
import com.example.batisproject.entity.Gather;
import com.example.batisproject.entity.GatherComment;
import com.example.batisproject.entity.Location;
import com.example.batisproject.entity.User;
import com.example.batisproject.mapper.Yk_fileInfoMapper;
import com.example.batisproject.mapper.jungi.CategoryMapper;
import com.example.batisproject.mapper.yk.Yk_categoryMapper;
import com.example.batisproject.mapper.yk.Yk_gatherMapper;
import com.example.batisproject.mapper.yk.Yk_gather_commentMapper;
import com.example.batisproject.mapper.yk.Yk_locationMapper;
import com.example.batisproject.mapper.yk.Yk_userMapper;
import com.example.batisproject.service.yk.Yk_categoryService;
import com.example.batisproject.service.yk.Yk_file_info_Service;
import com.example.batisproject.service.yk.Yk_gatherService;
import com.example.batisproject.service.yk.Yk_gather_commentService;
import com.example.batisproject.service.yk.impl.Yk_categoryServiceImpl;
import com.example.batisproject.service.yk.impl.Yk_gatherServiceImpl;
import com.example.batisproject.service.yk.impl.Yk_gather_commnetServiceImpl;
import com.example.batisproject.service.yk.impl.Yk_userServiceImpl;

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
    
    
    @Autowired
    Yk_gatherMapper gm;
    
    @Autowired
    Yk_gatherService gatherService2;
        
    @Autowired
    Yk_gatherServiceImpl gatherS;

    @Autowired
    Yk_gather_commentMapper commentMapper;

    @Autowired
    Yk_file_info_Service file_info_Service;
     

    @Autowired
    ModelMapper modelMapper;

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

    @Test
    void toLocalDateTime(){
        String test = "2023-11-23";
        LocalDateTime time = gatherService2.toLocalDateTime(test);

        
        assertNotNull(time);

    }

    @Test
    void get_GatherTest(){
        GatherDTO dto = gatherS.get_Gather(31L);
        // assertNull(dto);
        assertNotNull(dto);

    }

    @Test
    void gatherMapperTest(){
        Long g_id = 39L;
        Gather g = gm.get_Gather(g_id);
        assertNotNull(g);
    }

    @Test
    void gMapperRegisterTest(){
        LocalDateTime d = LocalDateTime.now();

        Gather g = Gather.builder()
        .category(1L)
        .content("tq")
        .endDate(d)
        .location(1)
        .peopleNum(10)
        .startDate(d)
        .title("tq")
        .user(7L)
        .build();

        int r = gm.gatherRegister(g);
        assertNotNull(g);
    }

    @Test
    void gatherRegiTest(){
        Long g_id = 31L;
        int r =commentMapper.peopleCount(g_id);

        // assertNull(r);
        assertNotNull(r);
    }

    @Test
    void getF(){
        FileInfo ff=fileInfoMapper.getFileInfo(31L);
        FileInfoDTO dto =file_info_Service.getFileInfo(31L);
        assertNotNull(dto);

    }

    @Test
    void checkRoleTest(){
        GatherComment comment = GatherComment.builder()
        .user(10L)
        .gather(39L)
        .build();
        // int role = commentMapper.checkRole(comment);
        String role = commentMapper.checkRole(comment);
        assertNull(role);

        
    }

    Yk_gather_commnetServiceImpl cs= new Yk_gather_commnetServiceImpl();

    @Autowired
    Yk_gather_commentService cms; 

    @Test
    void roleService(){
        GatherCommentDTO dto = new GatherCommentDTO();
        dto.setUser(8L);
        dto.setGather(24L);
        int r=cms.checkRole(dto);

        assertNotNull(r);
    }

    @Test
    void roleMapper(){
        GatherComment dto = new GatherComment();
        dto.setUser(10L);
        dto.setGather(21L);
        String r =commentMapper.checkRole(dto);
        assertNull(r);
    }

   

    
    @Test
    void joinOk(){
        GatherComment en = new GatherComment();
        en.setUser(15L);
        en.setGather(100L);

        int r= commentMapper.joinOk(en);
        assertNull(r);
    }
    
    @Autowired
    Yk_userMapper userMapper;

    @Autowired
    Yk_userSevice userSevice;

    @Test
    void userIdTest(){
        User user = userMapper.idToNick(8L);
        
        UserDTO dto = modelMapper.map(user, UserDTO.class);

        assertNull(dto);


    }
    
    @Autowired
    Yk_gather_commentService commentService;

    @Test
    void userIdListTest(){
        List<GatherComment> joinListBefor  =commentMapper.getJoinList(26L);

        List<GatherCommentDTO> joinList = joinListBefor.stream()
        .map(GatherComment->modelMapper.map(GatherComment, GatherCommentDTO.class))
        .collect(Collectors.toList());

        List<UserDTO> userList =commentService.nicknameList(joinList);


        assertNull(userList);

    }

}
