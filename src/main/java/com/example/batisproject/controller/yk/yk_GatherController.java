package com.example.batisproject.controller.yk;


import java.util.List;
import com.example.batisproject.service.yk.Yk_locationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.batisproject.annotaion.CurrentUser;
import com.example.batisproject.controller.AuthenticationForModel;
import com.example.batisproject.dto.CategoryDTO;
import com.example.batisproject.dto.FileInfoDTO;
import com.example.batisproject.dto.GatherCommentDTO;
import com.example.batisproject.dto.GatherDTO;
import com.example.batisproject.dto.GatherImageDTO;
import com.example.batisproject.dto.LocationDTO;
import com.example.batisproject.dto.UserDTO;
import com.example.batisproject.entity.User;
import com.example.batisproject.service.category.CategoryService;
import com.example.batisproject.service.user.UserService;
import com.example.batisproject.service.yk.Yk_categoryService;
import com.example.batisproject.service.yk.Yk_file_info_Service;
import com.example.batisproject.service.yk.Yk_gatherService;
import com.example.batisproject.service.yk.Yk_gather_commentService;

@Controller
public class yk_GatherController {
    
    
    
    @Autowired
    private UserService userService;

    @Autowired
    private Yk_locationService locationService;

    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private Yk_gatherService gatherService;

    @Autowired
    private Yk_file_info_Service file_info_Service;

    @Autowired
    Yk_gather_commentService commentService;

    @Autowired
    private Yk_categoryService yk_categoryService;
    


    //글쓰기 폼으로 이동
    @GetMapping("/user/gather/register")
    public String regiser(Model model){
        //유저 던지기 사이드바에 유저 머니 있어서 매번넣어야함
        User user = new AuthenticationForModel().getAuthentication();
        UserDTO userDTO = userService.existsByEmail(user.getUsername());
        model.addAttribute("user", userDTO);

        //로케이션 던지기
        List<LocationDTO> locationList = locationService.getList();
        model.addAttribute("locationList", locationList);

        //카테고리 던지기
        List<CategoryDTO> categoryList =categoryService.getAllMainCategory();
        model.addAttribute("categoryList", categoryList);

        



        return "gather/register";
    }

    
    //모임생성

    @PostMapping("/user/gather/register")
    public String register(MultipartFile file, Model model,@RequestParam("beforStartDate")String beforStartDate,
                            @RequestParam("detailName")String detailName, @RequestParam("beforEndDate")String beforEndDate,GatherDTO gatherDTO ){
        int result = 0;
        Long fileID =0L;
        Long gatherID = 0L;
        //유저 던지기 사이드바에 유저 머니 있어서 매번넣어야함
        User user = new AuthenticationForModel().getAuthentication();
        UserDTO userDTO = userService.existsByEmail(user.getUsername());
        model.addAttribute("user", userDTO);
        

      
        gatherDTO = gatherService.mergeDTO(detailName, gatherDTO, userDTO.getId(), beforStartDate, beforEndDate, gatherID);

        
        //글쓰기 
        gatherID = gatherService.gatherRegister(gatherDTO);
        if(gatherID<0){
            return "gather/register";
        }

        GatherCommentDTO commentDTO = GatherCommentDTO.builder()
            .gather(gatherID)
            .user((long)user.getId())
            .build();
            
        commentService.registerComment(commentDTO);
        
        // 파일 저장하기
        
            fileID = file_info_Service.inputImg(file,gatherID);


         
            
            if(fileID<0){
                return "gather/register";
            }
        

        
        return "user/main";
    }


    //디테일 - 
    @GetMapping("/user/gather/detail/{id}")
    public String detail(@PathVariable("id") Long g_id, Model model){
        //유저이름 실어보내기 세션막아놔서 이렇게 세션 대체임
        User user = new AuthenticationForModel().getAuthentication();
        UserDTO userDTO = userService.existsByEmail(user.getUsername());
        model.addAttribute("user",userDTO);
        //글번호 조회 후 글 정보 뿌려줘야함 
        GatherDTO gatherDTO = gatherService.get_Gather(g_id);
        model.addAttribute("gather",gatherDTO);
        //관리번호 코맨트 롤 뿌려줘야함
        GatherCommentDTO commentDTO = commentService.get_gather_userRole(g_id, (long)user.getId());  //이부분 체크 이상한데
        model.addAttribute("comment", commentDTO);
        //현재참여중인 인원 보여주기
        int peopleCounting = commentService.peopleCount(g_id);
        model.addAttribute("peopleCount", peopleCounting);
        //로케이션 동이랑, 카데고리 디테일네임(카테고리) 받고 뿌려주기 
        // String locationDong = locationService.getLocation_Dong(gatherDTO.getLocation());
        model.addAttribute("locationDong", locationService.getLocation_Dong(gatherDTO.getLocation()));
        model.addAttribute("categoryName", yk_categoryService.getCategoryName(gatherDTO.getCategory()));
        //글번호랑 연관관계 이미지 뿌려줘야함
        FileInfoDTO fileInfoDTO = file_info_Service.getFileInfo(g_id);
        if(!fileInfoDTO.equals(null)){
            model.addAttribute("fileInfo", fileInfoDTO);
        }

        //글 조회수 카운트하기
        gatherService.viewCount(g_id);

        return "gather/gatherDetail";
    }

    //게시글 업데이트 요청
    @GetMapping("user/gather/detail/{g_id}/update")
    public String gatherUpdate(@PathVariable("g_id")Long g_id, @CurrentUser User user, Model model){
        //유저는 무조건 처음에 뿌려줘야 작동됨
        UserDTO userDTO = userService.existsByEmail(user.getUsername());
        model.addAttribute("user", userDTO);

        Long gatherAdmin = gatherService.gatherToUser(g_id);
        if(gatherAdmin!=(long)userDTO.getId()){
            return "redirect:/user/main";
        }
        
        GatherDTO gatherDTO = gatherService.get_Gather(g_id);
        model.addAttribute("gather", gatherDTO);

        //로케이션 던지기
        List<LocationDTO> locationList = locationService.getList();
        model.addAttribute("locationList", locationList);

        //카테고리 던지기
        List<CategoryDTO> categoryList =categoryService.getAllMainCategory();
        model.addAttribute("categoryList", categoryList);

        return "gather/gatherUpdate";
    }


    //게시글 업데이트 
    @PostMapping("user/gather/detail/{g_id}/update")
    public String gatherUpdate(@PathVariable("g_id")Long g_id, @CurrentUser User user, Model model,@RequestParam("beforStartDate")String beforStartDate,
                                     @RequestParam("detailName")String detailName, @RequestParam("beforEndDate")String beforEndDate,GatherDTO gatherDTO,
                                     MultipartFile file){
        UserDTO userDTO = userService.existsByEmail(user.getUsername());
        model.addAttribute("user", userDTO);

        gatherDTO = gatherService.mergeDTO(detailName, gatherDTO, userDTO.getId(), beforStartDate, beforEndDate,g_id);
        int result = gatherService.gatherUpdate(gatherDTO);
        if(result<0){
            return "redirect:/user/gather/detail/"+g_id+"/update";
        }
        
        // 파일 저장하기
        
        Long fileID = file_info_Service.inputImgOrDelete(file,g_id);
        



        return "redirect:/user/gather/detail/"+g_id;
    }



    @GetMapping("/user/gather/detail/{g_id}/delete")
    public String deleteResither(@PathVariable("g_id")Long g_id, @CurrentUser User user, Model model){
        UserDTO userDTO = userService.existsByEmail(user.getUsername());
        model.addAttribute("user", userDTO);

        Long userId=gatherService.gatherToUser(g_id);
        if(userId!=userDTO.getId()){
            return "redirect:/user/main";
        }
        file_info_Service.deleteFileImg(g_id);

        commentService.deleteGatherIdTocomment(g_id);

        int result = gatherService.deleteResiter(g_id);
        if(result<0){
            return "redirect:/user/main";
        }


        return "redirect:/user/main";
    }


}
