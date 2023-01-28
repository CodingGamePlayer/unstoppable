package com.example.batisproject.service.user.impl;

import com.example.batisproject.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    void selectAll() {

        List<User> users = userService.selectAll();
        users.forEach(user -> log.info(user.toString()));

    }
}