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
    public int peopleCount(Long u_id, Long g_id) {
        int result = commentMapper.peopleCount(u_id, g_id);
        return result;
    }



}
