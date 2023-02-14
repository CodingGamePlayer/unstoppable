package com.example.batisproject.apicontroller.yk;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.batisproject.entity.GatherCommentMessage;
import com.example.batisproject.service.yk.Yk_gather_commentService;

@RestController
public class Yk_CommentRestContoroller {
    
    @Autowired
    Yk_gather_commentService commentService;

    @GetMapping("/user/gather/detail/{g_id}/comment/findCommentList")
    public List<GatherCommentMessage> commentRefly(@PathVariable("g_id")Long g_id){
        System.out.println("코멘트 비동기컴트롤 진입");
        List<GatherCommentMessage> commentMessageList =commentService.findCommentList(g_id);
        System.out.println(commentMessageList.toString());


        return  commentMessageList;
    }


    @GetMapping("Lt")
    public List<GatherCommentMessage> commentRefly2(@PathVariable("g_id")Long g_id){
        System.out.println("코멘트 비동기컴트롤 진입");
        List<GatherCommentMessage> commentMessageList =commentService.findCommentList(g_id);
        System.out.println(commentMessageList.toString());


        return  commentMessageList;
    }

}


