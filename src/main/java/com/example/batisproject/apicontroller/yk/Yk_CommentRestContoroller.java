package com.example.batisproject.apicontroller.yk;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.batisproject.dto.ChattingDTO;
import com.example.batisproject.entity.GatherCommentMessage;
import com.example.batisproject.service.yk.Yk_gather_commentService;

@RestController
public class Yk_CommentRestContoroller {
    
    @Autowired
    Yk_gather_commentService commentService;

 

    //댓글 요청
    @GetMapping("/user/gather/detail/{g_id}/comment/findCommentList")
    @ResponseBody
    public ResponseEntity<?> commentRefly(@PathVariable("g_id")Long g_id){
        List<ChattingDTO> chattingList =commentService.findCommentList(g_id);

        
        if(chattingList==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


        return  ResponseEntity.status(HttpStatus.OK).body(chattingList);
    }

    //댓글쓰기
    @PostMapping("/user/gather/detail/{g_id}/comment/send")
    public ResponseEntity<?> chattingCommentRegister(@PathVariable("g_id")Long g_id, @RequestBody ChattingDTO chattingComment){

        int result = commentService.chattingCommentRegister(g_id, chattingComment);
        if(result<0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


        return ResponseEntity.status(HttpStatus.OK).build();
    }


    //댓글삭제
    @GetMapping("/user/gather/detail/{g_id}/comment/message/{messageID}")
    public ResponseEntity<?>messageDelete(@PathVariable("messageID")Long gcm_id){
        int result = commentService.deleteMessage(gcm_id);
        if(result<0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }
    

}


