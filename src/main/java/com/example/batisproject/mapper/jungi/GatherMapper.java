package com.example.batisproject.mapper.jungi;

import com.example.batisproject.entity.Gather;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface GatherMapper {

    @Select("Select * from gather")
    @Results(id = "gatherMap", value = {
            @Result(property = "id", column = "g_id"),
            @Result(property = "user", column = "u_id"),
            @Result(property = "location", column = "l_id"),
            @Result(property = "category", column = "c_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
            @Result(property = "regDate", column = "regdate"),
            @Result(property = "startDate", column = "startdate"),
            @Result(property = "endDate", column = "enddate"),
            @Result(property = "allDay", column = "allday"),
            @Result(property = "textColor", column = "textColor"),
            @Result(property = "backgroundColor", column = "backgroundColor"),
            @Result(property = "borderColor", column = "borderColor"),
            @Result(property = "viewCnt", column = "view_cnt"),
            @Result(property = "peopleNum", column = "people_num"),
            @Result(property = "point", column = "point")
    })
    List<Gather> getAll();


    @Select("select * from gather where u_id = (select u_id from user where nickname = #{nickname}) order by g_id desc")
    @ResultMap("gatherMap")
    List<Gather> getAllByNickname(@Param("nickname") String nickname);


    @Insert("INSERT INTO gather (u_id, l_id, c_id, title, content, startdate, enddate, allday, people_num, point) values " +
            " (#{gather.user}, #{gather.location}, #{gather.category}, #{gather.title}, #{gather.content}, #{gather.startDate}, #{gather.endDate}, #{gather.allDay}," +
            " #{gather.peopleNum}, #{gather.point})")
    @Options(useGeneratedKeys = true, keyProperty = "id") // 입력할 때 생성된 id를 바로 반환함.
    int insert(@Param("gather") Gather gather);

    @Select("select * from gather where c_id = #{category} order by g_id desc")
    @ResultMap("gatherMap")
    List<Gather> getByCategory(Integer category);

    @Select("select * from gather where c_id = #{category} and u_id = (select u_id from user where nickname = #{nickname}) order by g_id desc")
    @ResultMap("gatherMap")
    List<Gather> getByCategoryAndNickname(Integer category, String nickname);
}
