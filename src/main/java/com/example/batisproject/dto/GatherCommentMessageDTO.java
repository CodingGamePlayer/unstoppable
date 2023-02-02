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
public class GatherCommentMessageDTO {
    private Long id;    // gcm_id
    private Long gatherComment;  // gc_id
    private String body;    // chatting_message (gatherCommentMessage)
    private LocalDateTime regdate;  // 생성일 (default Now())
}
