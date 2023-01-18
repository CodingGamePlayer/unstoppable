package com.example.batisproject.service;

import com.example.batisproject.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    List<User> getAll();

    User getByEmailPassword(String email, String password);

    int insert(User user);

    User existsByEmail(String email);

    User existsByNickName(String nickname);
}
