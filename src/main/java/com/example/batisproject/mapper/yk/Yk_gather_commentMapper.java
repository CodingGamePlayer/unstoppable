package com.example.batisproject.mapper.yk;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.batisproject.entity.GatherComment;

import com.example.batisproject.entity.GatherCommentMessage;

import com.example.batisproject.entity.User;


@Mapper
public interface Yk_gather_commentMapper {
    
    //롤권한 0 참여신청도 안눌러서 테이블에 저장안되있는사람.  1 참여신청을 한사람.  2.참여취소한사람 3.참여된사람 4.마스터권한


    // 글쓰기 생성시 연관테이블 잡아주고 글쓴사람 권한 4로
    @Insert("insert into gather_comment (g_id,u_id,role) values (#{gather},#{user},4);") 
    int registerComment(GatherComment comment);


    //글참여
    @Insert("insert into gather_comment (g_id,u_id,role,joinMent) values (#{gather},#{user},1,#{joinMent});") 
    int joinComment(GatherComment comment);


    //참여취소 후 재참여
    @Update("update gather_comment set role=1,joinMent=#{joinMent} where g_id=#{gather} AND u_id =#{user};")
    int againJoin(GatherComment comment);

    //롤권한 객체로 가져오기 이거대신 체크롤써야하나 냅둬도 되나 고민좀해봐야함
    @Select("select * from gather_comment where u_id=#{u_id} && g_id= #{g_id};")
    @Results(id="commnet",value = {
        @Result(property = "user", column = "u_id"),
        @Result(property = "gather", column = "g_id"),
        @Result(property = "id", column = "gc_id")
    })
    GatherComment get_gather_userRole(Long g_id, Long u_id);

    
    //현재 참여자 몇명인지 알아오는 메소드 (수정필요 현재 권한 2(참가수락자)이상만 조회하지를 않고 참여신청까지 전부 조회가되니)
    @Select("select count(u_id) as cnt from gather_comment where role>=2 && g_id =#{g_id};")
    int peopleCount(Long g_id);


    //코멘트 참가신청 할수있게 글에 권한 조회해서 따오기   
    //  *! 중요 디비에 롤은 int 타입 이지만 기본값이 0이다 조회해서 없으면 null로 반환이 안되어서 int로 받으면 오류가 남 String 으로 받으면 디폴트값 0이 반환됨  !*
    @Select("select role from gather_comment where g_id = #{gather} && u_id= #{user};")
    String checkRole(GatherComment comment);





    // mysql에서 스브쿼리에 테이블이 메인쿼리테이블과 같아서 안되서 두개로 만들기
    // 참여 신청 취소
    @Update("update gather_comment set role=2,joinMent=null where gc_id = (select A.gc_id from(select gc_id from gather_comment where u_id = #{user} AND g_id = #{gather}) A );")
    int joinCancel(GatherComment comment);

    
    //참여자신청자 보기
    @Select("select * from gather_comment where g_id=#{g_id} AND role = 1;")
    @ResultMap("commnet")
    List<GatherComment> getJoinList(Long g_id);

    //권한참여수락
    @Update("update gather_comment set role=3 where gc_id = (select A.gc_id from(select gc_id from gather_comment where u_id = #{user} AND g_id = #{gather}) A );")
    int joinOk(GatherComment comment);


    //모임글에 참여한 사람들 닉네임 뽑아오기
    // @Select("select nickname from user where u_id = (select u_id from gather_comment where g_id=26 AND u_id=7);")
    // List<User>


    //글삭제를 위해 연관컬럼이있는 거 삭제
    @Delete("delete from gather_comment where g_id=#{g_id};")
    int deleteGatherIdTocomment(Long g_id);


    //댓글부러 오기 2단계 걸침

    //1단계
    @Select("select gc_id from gather_comment where g_id=#{g_id};")
    Long[] toFindGcIdList(Long g_id);
    //단계

    @Select("select * from gather_comment_message where gc_id = #{gc_id};")
    @Results(id="message",value = {
        @Result(property = "id", column = "gcm_id"),
        @Result(property = "gatherComment", column = "gc_id"),
        @Result(property = "body", column = "gcm_body"),
        @Result(property = "regdate", column = "gcm_regdate")
    })
    GatherCommentMessage findCommentList(Long gc_id);
    
}
