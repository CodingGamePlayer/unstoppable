package com.example.batisproject.mapper.jungi;


import com.example.batisproject.entity.Location;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LocationMapper {

    @Select("Select * from location")
    @Results(id = "locationMap", value = {
            @Result(property = "id", column = "l_id"),
            @Result(property = "sido", column = "sido"),
            @Result(property = "gugun", column = "gugun"),
            @Result(property = "dong", column = "dong")
    })
    List<Location> getAll();

    @Select("select * from location where sido not like 'none' ")
    @ResultMap("locationMap")
    List<Location> getAllSido();

    @Select("select * from location where dong like '%${dong}%'")
    @ResultMap("locationMap")
    List<Location> getAllDong(@Param("dong") String dong);
}
