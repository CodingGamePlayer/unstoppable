package com.example.batisproject.mapper;

import com.example.batisproject.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("Select * from User")
    @Results(id = "userMap", value = {
            @Result(property = "id", column = "u_id"),
            @Result(property = "username", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "regdate", column = "regdate"),
            @Result(property = "point", column = "point"),
            @Result(property = "role", column = "role")
    })
    List<User> getAll();


    @Select("select * from user where email = #{email} and password = #{password}")
    @ResultMap("userMap")
    User getByEmailPassword(@Param("email") String email, @Param("password") String password);

    @Insert("INSERT INTO user(email, password, nickname, regdate, point) VALUES ( #{user.username}, #{user.password}, #{user.nickname}, NOW(), #{user.point})")
    @Options(useGeneratedKeys = true, keyProperty = "id") // 입력할 때 생성된 id를 바로 반환함.
    int insert(@Param("user") User user);

    @Select("select * from user where email = #{email}")
    @ResultMap("userMap")
    User findByEmail(@Param("email") String email);

    @Select("select * from user where nickname = #{nickname}")
    @ResultMap("userMap")
    User existsByNickName(@Param("nickname") String nickname);

}
