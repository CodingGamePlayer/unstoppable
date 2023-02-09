package com.example.batisproject.service.yk;

import com.example.batisproject.dto.GatherCommentDTO;
import com.example.batisproject.entity.GatherComment;

public interface Yk_gather_commentService {
    
    //모임참여신청시
    int joinComment(GatherCommentDTO commentDTO);

    //참여중~관리까지 권한 가져오기
    GatherCommentDTO get_gather_userRole(Long g_id, Long u_id);

    //현재 참여중인 인원 가져오기
    int peopleCount(Long g_id);

    //유저별 롤권한 조회
    int checkRole(GatherCommentDTO commentDTO);

    //참여신청 취소
    int joinCancel(GatherCommentDTO commentDTO);

    //신청 취소후 재신청
    int againJoin(GatherCommentDTO commentDTO);

    //글생성시 연관테이블 잡아주면서 권한 4로 설정
    int registerComment(GatherCommentDTO commentDTO);
}
