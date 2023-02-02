package com.example.batisproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private int id;
    private int category;
    private int location;
    private String username; // 요거를 가져와야하니까
    private String password;
    private String nickname;
    private String regdate;
    private int point;
    private String role;
}



