package com.example.batisproject.mapper.yk;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.batisproject.entity.Location;


@Mapper
public interface Yk_locationMapper {
    


    @Select("select*from location;")
    @Results(id="locationMap",value = {
        @Result(property = "id", column = "l_id"),
        @Result(property = "sido", column = "sido"),
        @Result(property = "gugun", column = "gugun"),
        @Result(property = "dong", column = "dong")
    })
    public List<Location> getList();

    


}
