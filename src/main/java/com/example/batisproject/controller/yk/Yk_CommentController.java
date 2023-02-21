package com.example.batisproject.controller.yk;

import java.util.List;


import java.time.LocalDate;

import org.springframework.beans.factory.BeanFactoryExtensionsKt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.batisproject.dto.GatherDTO;
import com.example.batisproject.annotaion.CurrentUser;
import com.example.batisproject.controller.AuthenticationForModel;
import com.example.batisproject.dto.GatherCommentDTO;
import com.example.batisproject.dto.UserDTO;
import com.example.batisproject.entity.User;
import com.example.batisproject.service.user.UserService;
import com.example.batisproject.service.yk.Yk_gatherService;
import com.example.batisproject.service.yk.Yk_gather_commentService;
import com.example.batisproject.service.yk.Yk_userSevice;
import com.example.batisproject.dto.ChattingDTO;

@Controller
public class Yk_CommentController {
    
    @Autowired
    UserService userService;

    @Autowired
    Yk_gather_commentService commentService;

    @Autowired
    Yk_gatherService gatherService;

    @Autowired
    Yk_userSevice yUserSevice;


    
    

    //요청 승인 거절 하기
    @GetMapping("/user/gather/detail/{g_id}/commentAdmin")
    public String commentAdmin(@PathVariable("g_id")Long g_id,@CurrentUser User user ,Model model){

         //유저이름 실어보내기 세션막아놔서 이렇게 세션 대체임
         UserDTO userDTO = userService.existsByEmail(user.getUsername());
         model.addAttribute("user", userDTO);
         
         
         //모임참가 요청자들 정보 보내주기
         List<GatherCommentDTO> joinList =commentService.getJoinList(g_id);
         model.addAttribute("joinList", joinList);

        //참가자들 닉네임 실어다 주기
        List<UserDTO> userList = commentService.nicknameList(joinList);
        model.addAttribute("userList", userList);

        //참가자들 기본정보와 글번호 실어보내주기
        model.addAttribute("g_id", g_id);

        return "comment/commentAdmin";
    }
    

    


    //유저 참가상태별로 요청
    @PostMapping("/user/gather/detail/{g_id}/roleRequest")
    public String commentJoin(@PathVariable("g_id")Long g_id ,@CurrentUser User user,Model model,GatherDTO gatherDTO, String joinMent,RedirectAttributes rda ){

        UserDTO userDTO = userService.existsByEmail(user.getUsername());
        model.addAttribute("user", userDTO);

        
        GatherCommentDTO commentDTO = new GatherCommentDTO();
        commentDTO.setUser((long)userDTO.getId());
        commentDTO.setGather(g_id);
        commentDTO.setRole(commentService.checkRole(commentDTO));
        commentDTO.setJoinMent(joinMent);
        int result =0;
        String resultMessage = "참여포인트를 확인하세요";
        switch (commentDTO.getRole()) {
            case 0://모임참여신청
                result = gatherService.overPoincheck(gatherDTO, userDTO);
                if(result<=0){
                    rda.addFlashAttribute("resultMessage", resultMessage);
                    return "redirect:/user/gather/detail/"+g_id;
                }
                result = commentService.joinComment(commentDTO);
                if(result<=0){
                    return "redirect:/user/gather/detail/"+g_id;
                }
                break;
            case 1://모임참여 취소
                result = commentService.joinCancel(commentDTO);

                if(result<=0){

                    return "redirect:/user/gather/detail/"+g_id;
                }
                result = gatherService.userPointReset(gatherDTO.getPoint(), userDTO.getId());
                if(result<=0){
                    return "redirect:/user/gather/detail/"+g_id;
                }
                break;
            case 2://모임재참여
                result = gatherService.overPoincheck(gatherDTO, userDTO);
                if(result<=0){
                    rda.addFlashAttribute("resultMessage", resultMessage);
                    return "redirect:/user/gather/detail/"+g_id;
                }
                
                result = commentService.againJoin(commentDTO);

                if(result<=0){
                    return "redirect:/user/gather/detail/"+g_id;
                }
                break;
            default://3~4 번 채팅방진입

                List<GatherCommentDTO> joinList =commentService.toJoinList(g_id);
                model.addAttribute("joinList", joinList);
                    
                //참가자들 닉네임 실어다 주기
                List<UserDTO> userList = commentService.nicknameList(joinList);
                model.addAttribute("userList", userList);

                //채팅 던지기(비동기로도 던졌지만 뷰구현위해 일반컨트롤에서도 던짐)
                List<ChattingDTO> chattingList = commentService.findCommentList(g_id);
                model.addAttribute("chatList", chattingList);
        
                //참가자들 기본정보와 글번호 실어보내주기
                gatherDTO = gatherService.get_Gather(g_id);
                model.addAttribute("gather", gatherDTO);
                LocalDate startDate = gatherService.tLocalDate(gatherDTO.getStartDate());
                model.addAttribute("startDate", startDate);
                return "comment/gatherComment";

                
        }
        //요청 성공적 완료

        return "redirect:/user/gather/detail/"+g_id;

    }


    @PostMapping("/user/gather/detail/{g_id}/commentAdmin/joinOk")

    public String joinOk(@PathVariable("g_id")Long g_id, @RequestParam("userId")Long userId[]){
        commentService.joinOks(userId, g_id);
        return "redirect:/user/gather/detail/"+g_id+"/commentAdmin";
    }   

    @PostMapping("/user/gather/detail/{g_id}/commentAdmin/joinNo")
    public String joinNo(@PathVariable("g_id")Long g_id, Long userId[]){
        commentService.joinNos(userId, g_id);
        
        return "redirect:/user/gather/detail/"+g_id+"/commentAdmin";

    }   

}
