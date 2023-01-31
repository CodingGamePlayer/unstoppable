package com.example.batisproject.service.user;

import com.example.batisproject.entity.User;
import com.example.batisproject.dto.UserDTO;

import java.util.List;


public interface UserService {

    List<UserDTO> getAll();

    UserDTO getByEmailPassword(String email, String password);

    int insert(UserDTO userDTO);

    UserDTO existsByEmail(String email);

    UserDTO existsByNickName(String nickname);

    List<User> selectAll();

    int updateUser(UserDTO userDTO);

    int updateUserByLocation(UserDTO userDTO);
}
