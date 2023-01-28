package com.example.batisproject.mapper;

import com.example.batisproject.dto.PageRequestDTO;
import com.example.batisproject.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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


    @Test
    void selectAllForPaging() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        List<User> users = userMapper.selectAllForPaging(pageRequestDTO);

        users.forEach(user -> log.info(user.toString()));

    }

    @Test
    void getCount() {

        int count = userMapper.getCount();

        log.info(String.valueOf(count));
    }


    @Test
    void searchedUser() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .type("nickname")
                .keyword("�ڸ�")
                .build();

        List<User> users = userMapper.searchedUser(pageRequestDTO);
        users.forEach(user -> log.info(user.toString()));
    }

    @Test
    void selectAll() {

        List<User> users = userMapper.selectAll();

        users.forEach(user -> log.info(user.toString()));


    }

}