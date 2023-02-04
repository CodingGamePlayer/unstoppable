package com.example.batisproject.controller.yk;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import com.example.batisproject.service.yk.Yk_locationService;
import com.example.batisproject.service.yk.impl.Yk_File_info_ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.batisproject.controller.AuthenticationForModel;
import com.example.batisproject.dto.CategoryDTO;
import com.example.batisproject.dto.GatherDTO;
import com.example.batisproject.dto.LocationDTO;
import com.example.batisproject.dto.UserDTO;
import com.example.batisproject.entity.User;
import com.example.batisproject.service.category.CategoryService;
import com.example.batisproject.service.user.UserService;
import com.example.batisproject.service.yk.Yk_categoryService;
import com.example.batisproject.service.yk.Yk_file_info_Service;
import com.example.batisproject.service.yk.Yk_gatherService;

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
    private Yk_categoryService yk_categoryService;
    

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


    @PostMapping("/user/gather/register")
    public String register(MultipartFile file, Model model,@RequestParam("beforStartDate")String beforStartDate,
                            @RequestParam("detailName")String detailName, @RequestParam("beforEndDate")String beforEndDate,GatherDTO dto ){
        System.out.println("컨트롤 진입");
        int result = 0;
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

        // 파일 저장하기
        if(!file.equals(null)){
            result = file_info_Service.inputImg(file);
            System.out.println("서비스 완료 후");
            if(result<0){
                return "fale";
            }

        }

        //글쓰기 
        result = gatherService.gatherRegister(dto);
        if(result<0){
            System.out.println("글작성 실패");
            return "fale";
        }
        
          



        System.out.println("글작성 성공");

        return "gather/register";
    }

}
