package com.example.batisproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GatherCommentDTO {
    private Long id;    // gc_id
    private Long gather; // g_id
    private Long user;   // u_id
    private int role;    // 채팅 권한
    private String joinMent;
}
