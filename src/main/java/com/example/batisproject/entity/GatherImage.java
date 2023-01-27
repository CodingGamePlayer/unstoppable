package com.example.batisproject.entity;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GatherImage {

    private Long id;
    private Long fileInfo;  // file_info_id
    private Long gather;    // gather_id
}
