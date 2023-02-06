package com.example.batisproject.service.yk;

import com.example.batisproject.dto.GatherCommentDTO;
import com.example.batisproject.entity.GatherComment;

public interface Yk_gather_commentService {
    
    int register_commnet(GatherCommentDTO commentDTO);

    GatherCommentDTO get_gather_userRole(Long g_id, Long u_id);
}
