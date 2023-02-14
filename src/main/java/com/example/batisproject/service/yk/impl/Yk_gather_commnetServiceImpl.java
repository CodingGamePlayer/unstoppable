package com.example.batisproject.service.yk.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.batisproject.dto.GatherCommentDTO;
import com.example.batisproject.dto.UserDTO;
import com.example.batisproject.entity.GatherComment;
import com.example.batisproject.mapper.yk.Yk_gather_commentMapper;
import com.example.batisproject.service.yk.Yk_gather_commentService;
import com.example.batisproject.service.yk.Yk_userSevice;

import com.example.batisproject.entity.GatherCommentMessage;


@Service
public class Yk_gather_commnetServiceImpl implements Yk_gather_commentService {
    
    @Autowired
    Yk_gather_commentService commentService;

    @Autowired
    Yk_gather_commentMapper commentMapper;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    Yk_userSevice yUserSevice;

    @Override
    public int joinComment(GatherCommentDTO commentDTO) {
        
        GatherComment comment = modelMapper.map(commentDTO, GatherComment.class);
        

        return commentMapper.joinComment(comment);
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
        try {
            //웃긴게 조건에 널값비교이지만 널이아니기 때문에 통과해서 발생을 안시킴
            if(role.equals(null)){
               return 0; 
            }
        } catch (Exception e) {
            //널값 비교일때 에러를 발생시킴
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


    @Override
    public int registerComment(GatherCommentDTO commentDTO) {
        GatherComment comment = modelMapper.map(commentDTO, GatherComment.class);
        
        return commentMapper.registerComment(comment);
    }

    @Override
    public int againJoin(GatherCommentDTO commentDTO) {
        GatherComment comment = modelMapper.map(commentDTO, GatherComment.class);
        
        return commentMapper.againJoin(comment);
    }

    //참여신청자 관리 보여주기
    @Override
    public List<GatherCommentDTO> getJoinList(Long g_id) {
        List<GatherComment> joinListBefor = commentMapper.getJoinList(g_id);

        List<GatherCommentDTO> joinList = joinListBefor.stream()
        .map(GatherComment->modelMapper.map(GatherComment, GatherCommentDTO.class))
        .collect(Collectors.toList());

        return joinList;
    }

    @Override
    public int joinOk(GatherCommentDTO commentDTO) {
        GatherComment comment = modelMapper.map(commentDTO, GatherComment.class);
        commentMapper.joinOk(comment);
        return commentMapper.joinOk(comment);
    }

    //그정보로 유저아이디 조회해서 유저 닉네임 가져와서 보내주기 
    @Override
    public List<UserDTO> nicknameList(List<GatherCommentDTO> joinList) {
        List<UserDTO> userIdList= new ArrayList<>();
        
        

        for(int i=0; i<joinList.size(); i++){
            Long uId=joinList.get(i).getUser();
            userIdList.add(yUserSevice.idToNick(uId));
            
        }
        
        

        return userIdList;
    }

    @Override
    public int deleteGatherIdTocomment(Long g_id) {
        
        return commentMapper.deleteGatherIdTocomment(g_id);
    }


    //참가 다중 수락
    @Override
    public int joinOks(Long[] userId,Long g_id) {
        GatherCommentDTO commentDTO = new GatherCommentDTO();
        commentDTO.setGather(g_id);
        for(int i=0; i<userId.length; i++ ){
             commentDTO.setUser(userId[i]);
             int result =commentService.joinOk(commentDTO);
             if(result<0){
                return 0;
             }
        }


        return 1;
    }

    //참가 다중 거절
    @Override
    public int joinNos(Long[] userId,Long g_id) {
        GatherCommentDTO commentDTO = new GatherCommentDTO();
        commentDTO.setGather(g_id);
        for(int i=0; i<userId.length; i++ ){
             commentDTO.setUser(userId[i]);
             int result =commentService.joinCancel(commentDTO);
             if(result<0){
                return 0;
             }
        }


        return 1;
    }

    @Override
    public List<GatherCommentMessage>findCommentList(Long g_id){
        Long[] gcArray = commentMapper.toFindGcIdList(g_id);
        
        List<GatherCommentMessage> messageList = new ArrayList();
        
        for(int i=0; i<gcArray.length; i++){
            System.out.println(i);
            GatherCommentMessage messge =  commentMapper.findCommentList(gcArray[i]);
            messageList.add(messge);
        }

        return messageList;
    }


}
