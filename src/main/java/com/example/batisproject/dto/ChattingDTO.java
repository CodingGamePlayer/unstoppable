package com.example.batisproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChattingDTO implements Comparable<ChattingDTO> {// 채팅디티오를 리스트로 쓸때 아이디로 정렬을 위해   자바랭에 컴파라블 구현
    private int user;
    private String body;
    private Long messageId;
    private String userNick;

    @Override  //메세지 아이디순으로 오버라이드
    public int compareTo(ChattingDTO c) {
        if (c.messageId < messageId) {
            return 1;
        } else if (c.messageId > messageId) {
            return -1;
        }
        return 0;
    }
    


    
}
