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
                            @RequestParam("detailName")String detailName, @RequestParam("beforEndDate")String beforEndDate,GatherDTO dto ){
        System.out.println("컨트롤 진입");
        int result = 0;
        Long fileID =0L;
        Long gatherID = 0L;
        //유저 던지기 사이드바에 유저 머니 있어서 매번넣어야함
        User user = new AuthenticationForModel().getAuthentication();
        UserDTO userDTO = userService.existsByEmail(user.getUsername());
        model.addAttribute("user", userDTO);
        
            

        //디티오에 데이터타입변환후 정보담기
        dto.setCategory(yk_categoryService.CategoryId(detailName));
        dto.setUser((long)userDTO.getId());
        dto.setStartDate(gatherService.toLocalDateTime(beforStartDate));
        dto.setEndDate(gatherService.toLocalDateTime(beforEndDate));
        System.out.println(dto.toString());


        
        
        //글쓰기 
        gatherID = gatherService.gatherRegister(dto);
        if(gatherID<0){
            System.out.println("글작성 실패");
            return "gather/register";
        }

        GatherCommentDTO commentDTO = GatherCommentDTO.builder()
            .gather(gatherID)
            .role(4)
            .user((long)user.getId())
            .build();

        commentService.register_commnet(commentDTO);
        
        // 파일 저장하기
        if(!file.equals(null)){
            fileID = file_info_Service.inputImg(file);
            System.out.println("서비스 완료 후");


            // 글과 이미지 연관관계 테이블 디비 저장하기
            GatherImageDTO imgDTO = GatherImageDTO.builder()
            .fileInfo(fileID)
            .gather(gatherID)
            .build();
            
            
            
            file_info_Service.registerGather_img(imgDTO);
            System.out.println("사진 작성 성공");
            
            if(fileID<0){
                return "gather/register";
            }
        }   

        System.out.println("글작성 성공");

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
        


        //글번호랑 연관관계 이미지 뿌려줘야함
        FileInfoDTO fileInfoDTO = file_info_Service.getFileInfo(g_id);
        model.addAttribute("fileinfo", fileInfoDTO);

        return "gather/gatherDetail";
    }

    




}
