package com.example.batisproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    /*
    c_id bigint AI PK
    g_id varchar(5)
    c_lv int
    c_nm varchar(45)
    c_d_lv int
    c_d_nm varchar(255)
    c_p_lv int
    c_d_p_lv int
     */
    private Long id;
    private String groupId;
    private int lv;
    private String name;
    private int detailLV;
    private String detailName;
    private int parentLv;
    private int detailParentLv;

}
