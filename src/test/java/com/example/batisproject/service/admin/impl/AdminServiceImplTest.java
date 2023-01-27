package com.example.batisproject.service.admin.impl;

import com.example.batisproject.dto.PageRequestDTO;
import com.example.batisproject.dto.PageResponseDTO;
import com.example.batisproject.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class AdminServiceImplTest {

    @Autowired
    private AdminServiceImpl adminService;

    @Test
    void selectAllForPaging() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        PageResponseDTO<UserDTO> pageResponseDTO = adminService.selectAllForPaging(pageRequestDTO);

        log.info(pageResponseDTO.toString());


    }
}