package com.example.batisproject.mapper.yk;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.batisproject.entity.Location;


@Mapper
public interface Yk_locationMapper {
    
    @Select("select*from location;")
    public List<Location> getList();

    


}
