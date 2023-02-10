package com.example.batisproject.service.yk.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.batisproject.dto.UserDTO;
import com.example.batisproject.entity.User;
import com.example.batisproject.mapper.yk.Yk_userMapper;
import com.example.batisproject.service.yk.Yk_userSevice;

@Service
public class Yk_userServiceImpl implements Yk_userSevice {

    @Autowired
    Yk_userMapper userMapper;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserDTO idToNick(Long userId) {
        User user=userMapper.idToNick(userId);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);


        return userDTO;
    }
    
}
