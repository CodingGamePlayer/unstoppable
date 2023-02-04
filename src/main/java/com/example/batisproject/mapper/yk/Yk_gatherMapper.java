package com.example.batisproject.mapper.yk;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


import com.example.batisproject.entity.Gather;

@Mapper
public interface Yk_gatherMapper {
    
    
    @Insert("insert into gather (u_id,l_id,c_id,title,content,startdate,enddate,people_num,point) values (#{user},#{location},#{category},#{title},#{content},#{startDate},#{endDate},#{peopleNum},#{point});")
    int gatherRegister(Gather entityGather);
}
