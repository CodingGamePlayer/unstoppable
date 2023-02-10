package com.example.batisproject.mapper.yk;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.batisproject.entity.User;

@Mapper
public interface Yk_userMapper {
    
    //유저아이디로 닉네임 가져오기 코멘트에 이름뿌려줄때 필요함
    @Select("select nickname from user where u_id = #{userId};")
   
    User idToNick(Long userId);
}
