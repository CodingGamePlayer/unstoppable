package com.example.batisproject.service.yk.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.batisproject.dto.GatherCommentDTO;
import com.example.batisproject.entity.GatherComment;
import com.example.batisproject.mapper.yk.Yk_gather_commentMapper;
import com.example.batisproject.service.yk.Yk_gather_commentService;

@Service
public class Yk_gather_commnetServiceImpl implements Yk_gather_commentService {
    
    @Autowired
    Yk_gather_commentService commentService;

    @Autowired
    Yk_gather_commentMapper commentMapper;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public int register_commnet(GatherCommentDTO commentDTO) {
        
        GatherComment comment = modelMapper.map(commentDTO, GatherComment.class);
        

        return commentMapper.registerGather_comment(comment);
    }

    @Override
    public GatherCommentDTO get_gather_userRole(Long g_id, Long u_id) {
        GatherComment comment = commentMapper.get_gather_userRole(g_id, u_id);
        GatherCommentDTO checkNullDTO = new GatherCommentDTO();

        try {
            GatherCommentDTO commentDTO = modelMapper.map(comment, GatherCommentDTO.class);
            return commentDTO;
        } catch (Exception e) {
            // TODO: handle exception
            return checkNullDTO;
        }

        
    }

    @Override
    public int peopleCount(Long g_id) {
        int result = commentMapper.peopleCount(g_id);
        return result;
    }



    @Override
    public int checkRole(GatherCommentDTO commentDTO) {
        GatherComment comment = modelMapper.map(commentDTO, GatherComment.class);
        String role= commentMapper.checkRole(comment);
        //글에 참여신청을 하지않으면 테이블에 저장된 값이 없으니 null반환 참여신청하지않은 사람은 권한 0
        if(role.equals(null)){
           return 0; 
        }

        return Integer.parseInt(role);
    }

    @Override
    public int joinCancel(GatherCommentDTO commentDTO) {
        GatherComment comment = modelMapper.map(commentDTO, GatherComment.class);
        int result = commentMapper.joinCancel(comment);
        
        return result;
    }



}
