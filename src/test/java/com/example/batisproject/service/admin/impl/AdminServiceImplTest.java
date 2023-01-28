package com.example.batisproject.service.admin.impl;

import com.example.batisproject.dto.GatherDTO;
import com.example.batisproject.dto.PageRequestDTO;
import com.example.batisproject.dto.PageResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class AdminServiceImplTest {

    @Autowired
    private AdminServiceImpl adminService;


    @Test
    void searchGather() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<GatherDTO> pageResponseDTO = adminService.searchGather(pageRequestDTO);

        List<GatherDTO> dtoList = pageResponseDTO.getDtoList();

        dtoList.forEach(gatherDTO -> log.info(gatherDTO.toString()));

    }
}