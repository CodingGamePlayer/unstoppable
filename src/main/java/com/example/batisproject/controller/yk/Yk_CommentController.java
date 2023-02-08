package com.example.batisproject.controller.yk;

import org.springframework.beans.factory.BeanFactoryExtensionsKt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.batisproject.annotaion.CurrentUser;
import com.example.batisproject.controller.AuthenticationForModel;
import com.example.batisproject.dto.GatherCommentDTO;
import com.example.batisproject.dto.UserDTO;
import com.example.batisproject.entity.User;
import com.example.batisproject.service.user.UserService;
import com.example.batisproject.service.yk.Yk_gather_commentService;

@Controller
public class Yk_CommentController {
    
    @Autowired
    UserService userService;

    @Autowired
    Yk_gather_commentService commentService;


    
    

    //요청이 온 이후
    @GetMapping("/user/gather/detail/{g_id}/commentAdmin")
    public String commentAdmin(@PathVariable("g_id")Long g_id, Model model){
        System.out.println("코멘트관리 컨트롤");
         //유저이름 실어보내기 세션막아놔서 이렇게 세션 대체임
         User user = new AuthenticationForModel().getAuthentication();
         UserDTO userDTO = userService.existsByEmail(user.getUsername());
         model.addAttribute("user", userDTO);




        return "comment/commentAdmin";
    }
    

    //유저 참가상태별로 요청
    @GetMapping("/user/gather/detail/{g_id}/roleRequest")
    public String commentJoin(@PathVariable("g_id")Long g_id ,@CurrentUser User user,Model model){
        UserDTO userDTO =userService.existsByEmail(user.getUsername());
        model.addAttribute("userDTO",userDTO);
        
        GatherCommentDTO commentDTO = new GatherCommentDTO();
        commentDTO.setUser((long)userDTO.getId());
        commentDTO.setGather(g_id);
        commentDTO.setRole(commentService.checkRole(commentDTO));
        int result =0;
        switch (commentDTO.getRole()) {
            case 0:
                result = commentService.joinComment(commentDTO);
                if(result>0){
                    return "redirect:/user/gather/detail/"+g_id;
                }
                break;
            case 1:
                result = commentService.joinCancel(commentDTO);
                if(result>0){
                    return "redirect:/user/gather/detail/"+g_id;
                }
                break;
            case 2:
                result = commentService.againJoin(commentDTO);
                if(result>0){
                    return "redirect:/user/gather/detail/"+g_id;
                }
                break;
            default:
                //3~4 번 채팅방진입
                return "/user/gather/"+g_id+"/comment";
                
        }

        //요청 성공적 완료
        return "/user/gather/detail/"+g_id;
    }


}
