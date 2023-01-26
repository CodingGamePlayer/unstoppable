package com.example.batisproject.mapper;

import com.example.batisproject.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("Select * from User")
    @Results(id = "userMap", value = {
            @Result(property = "id", column = "u_id"),
            @Result(property = "category", column = "c_id"),
            @Result(property = "location", column = "l_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "regdate", column = "regdate"),
            @Result(property = "point", column = "point"),
            @Result(property = "role", column = "role")
    })
    List<User> getAll();

    @Select("select * from user where c_id = 1 and l_id = 19000 and #{username}")
    @ResultMap("userMap")
    User getByEmailInit(@Param("username") String username);

    @Select("select * from user where email = #{username}")
    @ResultMap("userMap")
    User existsByEmail(@Param("username")  String username);

    @Select("select * from user where nickname = #{nickname}")
    @ResultMap("userMap")
    User existsByNickName(@Param("nickname") String nickname);


    @Select("select * from user where username = #{username} and password = #{password}")
    @ResultMap("userMap")
    User getByEmailPassword(@Param("username") String username, @Param("password") String password);

    @Insert("INSERT INTO user(username, password, nickname) VALUES (#{user.username}, #{user.password}, #{user.nickname})")
    @Options(useGeneratedKeys = true, keyProperty = "id") // 입력할 때 생성된 id를 바로 반환함.
    int insert(@Param("user") User user);



    // 회원 정보 수정

    @Update("update user set c_id = #{user.cId}, l_id = #{user.lId}, point = #{user.point}, role = #{user.role}" +
            " where username = #{user.username}")
    @ResultMap("userMap")
    boolean updateUser(@Param("user") User user);



}
