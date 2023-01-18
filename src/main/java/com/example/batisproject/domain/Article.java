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
public class Article {

    private int id;
    private String title;
    private String body;
    private LocalDate regdate;
    private LocalDate moddate;
    private String userid;


}
