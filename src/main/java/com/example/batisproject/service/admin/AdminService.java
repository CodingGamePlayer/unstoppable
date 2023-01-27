package com.example.batisproject.service.admin;

import com.example.batisproject.dto.PageRequestDTO;
import com.example.batisproject.dto.PageResponseDTO;
import com.example.batisproject.dto.UserDTO;
import com.example.batisproject.entity.User;

import java.util.List;

public interface AdminService {

    int update(UserDTO userDTO);

    PageResponseDTO<UserDTO> selectAllForPaging(PageRequestDTO pageRequestDTO);
}
