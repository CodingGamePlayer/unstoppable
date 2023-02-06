package com.example.batisproject.service.yk;

import java.time.LocalDateTime;

import com.example.batisproject.dto.GatherDTO;

public interface Yk_gatherService {
    Long gatherRegister(GatherDTO gatherDTO);    

    LocalDateTime toLocalDateTime(String date);

}
