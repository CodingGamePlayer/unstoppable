package com.example.batisproject.mapper.yk;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.batisproject.entity.GatherComment;


@Mapper
public interface Yk_gather_commentMapper {
    

    @Insert("insert into gather_comment (g_id,u_id,role) values (#{gather},#{user},#{role});") 
    int registerGather_comment(GatherComment comment);

    // @Select("select role from gather_comment where u_id=7;")




}
