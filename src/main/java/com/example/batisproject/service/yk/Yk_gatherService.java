package com.example.batisproject.service.yk;

import java.time.LocalDateTime;

import com.example.batisproject.dto.GatherDTO;

public interface Yk_gatherService {
    int gatherRegister(GatherDTO gatherDTO);    

    LocalDateTime toLocalDateTime(String date);

}
