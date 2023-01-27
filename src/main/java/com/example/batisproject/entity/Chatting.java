package com.example.batisproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Chatting {

    private Long id;    // ct_id
    private Long gather; // g_id
    private Long user;   // u_id
    private int role;    // 채팅 권한

}
