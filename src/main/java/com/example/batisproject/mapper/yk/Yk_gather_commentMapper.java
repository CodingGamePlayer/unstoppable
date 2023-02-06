package com.example.batisproject.mapper.yk;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.batisproject.entity.GatherComment;


@Mapper
public interface Yk_gather_commentMapper {
    

    @Insert("insert into gather_comment (g_id,u_id,role) values (#{gather},#{user},#{role});") 
    int registerGather_comment(GatherComment comment);

    @Select("select * from gather_comment where u_id=#{u_id} && #{g_id};")
    @Results(id="commnet",value = {
        @Result(property = "user", column = "u_id"),
        @Result(property = "gather", column = "g_id"),
        @Result(property = "id", column = "gc_id")
    })
    GatherComment get_gather_userRole(Long g_id, Long u_id);




}
