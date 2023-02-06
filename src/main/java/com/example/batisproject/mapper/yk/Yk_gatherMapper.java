package com.example.batisproject.mapper.yk;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.example.batisproject.entity.Gather;

@Mapper
public interface Yk_gatherMapper {
    
    
    @Insert("insert into gather (u_id,l_id,c_id,title,content,startdate,enddate,people_num,point) values (#{user},#{location},#{category},#{title},#{content},#{startDate},#{endDate},#{peopleNum},#{point});")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int gatherRegister(Gather entityGather);


    @Select("select*from gather where g_id = #{g_id};")
    Gather get_Gather(Long g_id);
}
