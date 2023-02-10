package com.example.batisproject.service.yk;

import java.time.LocalDateTime;

import com.example.batisproject.dto.GatherDTO;


public interface Yk_gatherService {
    //글쓰기 메소드
    Long gatherRegister(GatherDTO gatherDTO);

    //로컬데이트타임(시랑분까지 필요)로 변환 메소드
    LocalDateTime toLocalDateTime(String date);

    //글 디테일 하나 불러오기 메소드
    GatherDTO get_Gather(Long g_id);

    //글조회수 카운트하기
    int viewCount(Long g_id);

    //참여포인트 만큼 유저 포인트 차감
    int userPointMinus(Long point,int u_id);

    //참여취소,거절하면 포인트 복구
    int userPointReset(Long point,int u_id);

    //글 업데이트
    int gatherUpdate(GatherDTO gatherDTO);

    //글번호로 작성자 가지고오기
    Long gatherToUser(Long g_id);


    //글 받아오는게 다달라서 매개변수 넣으면 매차쿠차 디티오 완성
    GatherDTO mergeDTO(String CategoryName,GatherDTO dto,int userId,String beforStartDate, String beforEndDate,Long g_id);

    //글삭제 메소드
    int deleteResiter(Long g_id);
}
