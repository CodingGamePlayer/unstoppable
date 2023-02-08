package com.example.batisproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int id;
    private int category;
    private int location;
    private String username;
    private String password;
    private String nickname;
    private String regdate;
    private Long point;
    private String role;


}
