package com.example.batisproject.service;

import com.example.batisproject.domain.User;
import com.example.batisproject.dto.UserDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    List<User> getAll();

    UserDTO getByEmailPassword(String email, String password);

    int insert(UserDTO userDTO);

    UserDTO existsByEmail(String email);

    UserDTO existsByNickName(String nickname);
}
