package com.example.batisproject.service.admin.impl;

import com.example.batisproject.dto.PageRequestDTO;
import com.example.batisproject.dto.PageResponseDTO;
import com.example.batisproject.dto.UserDTO;
import com.example.batisproject.entity.User;
import com.example.batisproject.mapper.UserMapper;
import com.example.batisproject.service.admin.AdminService;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private UserMapper userMapper;

    @Override
    public int update(UserDTO userDTO) {

        System.out.println("before result : " + userDTO.toString());
        User user = userMapper.existsByEmail(userDTO.getUsername());

        if (user == null){
            throw new IllegalArgumentException("해당하는 유저가 존재하지 않습니다.");
        }
        
        user.setRole(userDTO.getRole());
        int result = userMapper.updateRole(user);

        if (! (result > 0))
            return 0;


        return 1;
    }

    @Override
    public PageResponseDTO<UserDTO> selectAllForPaging(PageRequestDTO pageRequestDTO) {
        List<User> userList = userMapper.selectAllForPaging(pageRequestDTO);
        List<UserDTO> dtoList = userList.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());

        int total = userMapper.getCount();

        PageResponseDTO<UserDTO> pageResponseDTO = PageResponseDTO.<UserDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
        return pageResponseDTO;
    }
}
