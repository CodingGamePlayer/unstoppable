package com.example.batisproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChattingMessageDTO {
    private Long id;
    private Long chatting;  // ct_id
    private String body;    // chatting_message
    private LocalDateTime regdate;  // 생성일 (default Now())
}
