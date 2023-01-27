package com.example.batisproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO {
    private int id;
    private String sido;
    private String gugun;
    private String dong;
}
