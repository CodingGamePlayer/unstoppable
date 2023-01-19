package com.example.batisproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private LocalDate regdate;
    private int point;
    private String role;

}
