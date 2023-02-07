package com.example.batisproject.mapper.jungi;

import com.example.batisproject.dto.GatherDTO;
import com.example.batisproject.dto.GatherResponseDTO;
import com.example.batisproject.dto.PageRequestDTO;
import com.example.batisproject.entity.Gather;
import com.example.batisproject.entity.GatherResponse;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface GatherMapper {

    @Select("Select * from gather where enddate > now() order by g_id desc")
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
            @Result(property = "modifyDate", column = "modifydate"),
            @Result(property = "allDay", column = "allday"),
            @Result(property = "textColor", column = "textColor"),
            @Result(property = "backgroundColor", column = "backgroundColor"),
            @Result(property = "borderColor", column = "borderColor"),
            @Result(property = "viewCnt", column = "view_cnt"),
            @Result(property = "peopleNum", column = "people_num"),
            @Result(property = "point", column = "point")
    })
    List<Gather> getAll();

    @Select("select * from gather where l_id = #{location} and enddate > now() order by g_id desc")
    @ResultMap("gatherMap")
    List<Gather> getAllByLocation(Integer location);


    @Select("select * from gather where u_id = (select u_id from user where nickname = #{nickname}) and enddate > now() order by g_id desc")
    @ResultMap("gatherMap")
    List<Gather> getAllByNickname(@Param("nickname") String nickname);

    @Insert("INSERT INTO gather (u_id, l_id, c_id, title, content, startdate, enddate, people_num, point) values " +
            " (#{gather.user}, #{gather.location}, #{gather.category}, #{gather.title}, #{gather.content}, #{gather.startDate}, #{gather.endDate}," +
            " #{gather.peopleNum}, #{gather.point})")
    @Options(useGeneratedKeys = true, keyProperty = "id") // auto_increment할 id설정
    int insert(@Param("gather") Gather gather);

    @Select("select * from gather where c_id = #{category} and enddate > now() order by g_id desc")
    @ResultMap("gatherMap")
    List<Gather> getByCategory(Integer category);

    @Select("select * from gather where c_id = #{category} and u_id = (select u_id from user where nickname = #{nickname}) and enddate > now() order by g_id desc")
    @ResultMap("gatherMap")
    List<Gather> getByCategoryAndNickname(Integer category, String nickname);
    @Select("select count(*) from gather")
    int getCount(PageRequestDTO pageRequestDTO);

    List<GatherResponse> selectMyList(PageRequestDTO pageRequestDTO);

    List<Gather> selectOtherList(PageRequestDTO pageRequestDTO);

    int getOtherListCount(PageRequestDTO pageRequestDTO);
    int getMyListCount(PageRequestDTO pageRequestDTO);

}
