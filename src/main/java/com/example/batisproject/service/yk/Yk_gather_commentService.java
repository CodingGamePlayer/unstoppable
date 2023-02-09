package com.example.batisproject.service.yk;

import com.example.batisproject.dto.GatherCommentDTO;
import com.example.batisproject.entity.GatherComment;

public interface Yk_gather_commentService {
    
    int register_commnet(GatherCommentDTO commentDTO);

    //참여중~관리까지 권한 가져오기
    GatherCommentDTO get_gather_userRole(Long g_id, Long u_id);

    //현재 참여중인 인원 가져오기
    int peopleCount(Long u_id,Long g_id);
}
