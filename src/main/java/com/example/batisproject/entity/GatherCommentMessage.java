package com.example.batisproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GatherCommentMessage {
    
    private Long id;
    private Long gatherComment;  // gc_id
    private String body;    // chatting_message (gatherCommentMessage)
    private LocalDateTime regdate;  // 생성일 (default Now())
}
