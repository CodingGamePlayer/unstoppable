package com.example.batisproject.service.impl;

import com.example.batisproject.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    void insert() {

        UserDTO userDTO = UserDTO.builder()
                .build();

        int insert = userService.insert(userDTO);
        log.info("inset : " + insert);
    }
}