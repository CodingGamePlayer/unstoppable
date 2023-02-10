package com.example.batisproject.mapper.yk;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.batisproject.entity.Gather;

@Mapper
public interface Yk_gatherMapper {
    
    //글작성
    @Insert("insert into gather (u_id,l_id,c_id,title,content,startdate,enddate,people_num,point) values (#{user},#{location},#{category},#{title},#{content},#{startDate},#{endDate},#{peopleNum},#{point});")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int gatherRegister(Gather entityGather);

    //글번호로 디테일조회
    @Select("select*from gather where g_id = #{g_id};")
    @Results(id = "gather", value ={
        @Result(property = "id", column = "g_id", id = true),
        @Result(property = "user", column = "u_id"),
        @Result(property = "location", column = "l_id"),
        @Result(property = "category", column = "c_id"),
        @Result(property = "regDate", column = "regdate"),
        @Result(property = "startDate", column = "startdate"),
        @Result(property = "endDate", column = "enddate"),
        @Result(property = "modifyDate", column = "modifydate"),
        @Result(property = "allDay", column = "allday"),
        @Result(property = "textColor", column = "textcolor"),
        @Result(property = "backgroundColor", column = "backgroundcolor"),
        @Result(property = "borderColor", column = "bordercolor"),
        @Result(property = "peopleNum", column = "people_num"),
        @Result(property = "viewCnt", column = "view_cnt")
    })
    Gather get_Gather(Long g_id);

    //글조회수 카운트
    @Update("update gather set view_cnt= view_cnt+ 1 where g_id = #{g_id};")
    int viewCount(Long g_id);

    //모임참여하면 참여포인트 차감
    @Update("update user set point=point-#{point} where u_id =#{u_id};")
    int userPointMinus(Long point,int u_id);
    

    //참여취소,거절하면 포인트 복구
    @Update("update user set point=point+#{point} where u_id =#{u_id};")
    int userPointReset(Long point,int u_id);
 
    
    //글업데이트
    @Update("update gather set l_id=#{location},c_id=#{category},title=#{title},content=#{content},startdate=#{startDate},enddate=#{endDate},modifydate= NOW(), people_num=#{peopleNum},point=#{point} where g_id=#{id};")
    int gatherUpdate(Gather gather);

    //글번호로 유저아이디 가져오기
    @Select("select u_id from gather where g_id=#{g_id}")
    Long gatherToUser(Long g_id);

    @Delete("delete from gather where g_id =#{g_id}")
    int deleteResiter(Long g_id);

    
}
