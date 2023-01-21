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
    private int point;
    private String role;

    private String provider;
    private String registrationId;

}
