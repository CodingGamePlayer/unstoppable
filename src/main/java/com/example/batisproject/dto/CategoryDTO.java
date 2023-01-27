package com.example.batisproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {
    private Long id;
    private String groupId;
    private int lv;
    private String name;
    private int detailLV;
    private String detailName;
    private int parentLv;
    private int detailParentLv;
}


