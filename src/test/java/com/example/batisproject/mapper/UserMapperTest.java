package com.example.batisproject.mapper;

import com.example.batisproject.domain.User;
import com.example.batisproject.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void getAll() {
    }

    @Test
    void getByEmailPassword() {
    }

    @Test
    void insert() {

        User user = User.builder()
                .password("123123")
                .nickname("none112")
                .point(1000000)
                .role("ROLE_USER")
                .build();

        int insert = userMapper.insert(user);
        log.info("User : " + user.toString());
        log.info("insert : " + insert);
    }

    @Test
    void existsByEmail() {
    }

    @Test
    void existsByNickName() {
    }
}