package com.example.batisproject.mapper;

import com.example.batisproject.dto.PageRequestDTO;
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

    @Select("select * from user where username = #{username}")
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



    // 회원 정보 수정 (해당 mapper는 수정이 필요해 보임)
    @Update("update user set c_id = #{user.category}, l_id = #{user.location}, point = #{user.point}, role = #{user.role}" +
            " where username = #{user.username}")
    int updateUser(@Param("user") User user);

    // 유저권한을 변경하기 위한 mapper
    @Update("UPDATE user SET role = #{user.role} WHERE u_id = #{user.id}")
    int updateRole(@Param("user") User user);


    //페이징처리를 위한 메소드
    @Select("SELECT * FROM user " +
            "ORDER BY u_id DESC " +
            "LIMIT #{pageRequestDTO.skip}, #{pageRequestDTO.size}")
    @ResultMap("userMap")
    List<User> selectAllForPaging(@Param("pageRequestDTO")PageRequestDTO pageRequestDTO);

    // 유저의 수를 가져오는 메소드
    int getCount();

    // 검색 기능을 사용한 mapper
    List<User> searchedUser(PageRequestDTO pageRequestDTO);

    // xml 테스트용 mapper
    List<User> selectAll();



}
