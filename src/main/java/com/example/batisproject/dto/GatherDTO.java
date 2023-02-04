package com.example.batisproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GatherDTO {
    private Long id;
    private Long user;  // u_id
    private int location;   // l_id
    private Long category;  // c_id
    private String title;
    private String content;
    private LocalDateTime regDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime modifyDate;
    private boolean allDay;
    private String textColor;
    private String backgroundColor;
    private String borderColor;
    private int viewCnt;
    private int peopleNum;
    private Long point;
}
