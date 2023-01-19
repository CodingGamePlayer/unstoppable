package com.example.batisproject.controller.user;

import com.example.batisproject.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {

    @Autowired
    private UserController userController;
    @Test
    void signup() {
        UserDTO userDTO = UserDTO.builder()
                .password("1111")
                .build();

        userController.signup(userDTO);

    }
}