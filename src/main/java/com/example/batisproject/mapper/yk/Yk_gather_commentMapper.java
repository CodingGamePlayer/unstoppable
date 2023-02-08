package com.example.batisproject.mapper.yk;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.batisproject.entity.GatherComment;


@Mapper
public interface Yk_gather_commentMapper {
    

    @Insert("insert into gather_comment (g_id,u_id,role) values (#{gather},#{user},#{role});") 
    int registerGather_comment(GatherComment comment);

    @Select("select * from gather_comment where u_id=#{u_id} && g_id= #{g_id};")
    @Results(id="commnet",value = {
        @Result(property = "user", column = "u_id"),
        @Result(property = "gather", column = "g_id"),
        @Result(property = "id", column = "gc_id")
    })
    GatherComment get_gather_userRole(Long g_id, Long u_id);

    //현재 참여자 몇명인지 알아오는 메소드 (수정필요 현재 권한 2(참가수락자)이상만 조회하지를 않고 참여신청까지 전부 조회가되니)
    // select count(u_id) as cnt from gather_comment where role>=2 && g_id =31; 이렇게 바꾸면됨 던져줄때 g아이디만 던져주면되는군
    @Select("select count(u_id) as cnt from gather_comment where role>=2 && g_id =#{g_id};")
    int peopleCount(Long g_id);

    //코멘트 참가신청 할수있게 글에 권한 조회해서 따오기   
    //  *! 중요 디비에 롤은 int 타입 이지만 기본값이 0이다 조회해서 없으면 null로 반환이 안되어서 int로 받으면 오류가 남 String 으로 받으면 디폴트값 0이 반환됨  !*
    @Select("select role from gather_comment where g_id = #{gather} && u_id= #{user};")
    String checkRole(GatherComment comment);

    // mysql에서 스브쿼리에 테이블이 메인쿼리테이블과 같아서 안되서 두개로 만들기
    // 1.  select gc_id from gather_comment where u_id = 8 && g_id = 38; 
    // 2. update gather_comment set role=0 where gc_id = 16; 
    @Update("update gather_comment set role=0 where gc_id = (select A.gc_id from(select gc_id from gather_comment where u_id = 8 && g_id = 38) A );")
    int joinCancel(GatherComment comment);


}
