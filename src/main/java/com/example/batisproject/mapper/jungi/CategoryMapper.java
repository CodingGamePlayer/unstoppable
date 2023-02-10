package com.example.batisproject.mapper.jungi;


import com.example.batisproject.dto.CategoryDTO;
import com.example.batisproject.entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {

    /*
    private Long id;
    private String groupId;
    private int categoryLv;
    private String name;
    private int detailLV;
    private String detailName;
    private int parentLv;
    private int detailParentLv;
     */

    @Select("Select * from category")
    @Results(id = "categoryMap", value = {
            @Result(property = "id", column = "c_id"),
            @Result(property = "groupId", column = "cg_id"),
            @Result(property = "lv", column = "c_lv"),
            @Result(property = "name", column = "c_nm"),
            @Result(property = "detailLV", column = "c_d_lv"),
            @Result(property = "detailName", column = "c_d_nm"),
            @Result(property = "parentLv", column = "c_p_lv"),
            @Result(property = "detailParentLv", column = "c_d_p_lv")
    })
    List<Category> getAll();


    @Select("select * from category where c_nm = '대분류' and c_d_nm not like '선택안함'")
    @ResultMap("categoryMap")
    List<Category> getAllMainCategory();

    @Select("select c_d_nm from category where c_id = ${category} ")
    String getNamebyCategoryId(Integer category);

}
