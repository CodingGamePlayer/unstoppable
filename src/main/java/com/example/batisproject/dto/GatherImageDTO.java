package com.example.batisproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GatherImageDTO {
    private Long id;
    private Long fileInfo;  // file_info_id
    private Long gather;    // gather_id
}
