package com.example.batisproject.service.yk.impl;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.batisproject.dto.GatherDTO;
import com.example.batisproject.dto.UserDTO;
import com.example.batisproject.entity.Gather;
import com.example.batisproject.mapper.yk.Yk_gatherMapper;
import com.example.batisproject.service.yk.Yk_categoryService;
import com.example.batisproject.service.yk.Yk_gatherService;




@Service
public class Yk_gatherServiceImpl implements Yk_gatherService{

    @Autowired
    private Yk_gatherMapper gatherMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Yk_categoryService categoryService;



    //글작성
    @Override
    public Long gatherRegister(GatherDTO gatherDTO) {
        
        Gather gather = modelMapper.map(gatherDTO, Gather.class);
        gatherMapper.gatherRegister(gather);
        return gather.getId();
    }


    //스트링에서 로컬데이터타입 으로 전환 메소드
    @Override
    public LocalDateTime toLocalDateTime(String date) {


        String hhmm = " 12:00";
        //데이터타입 스트링에서 로컬데이타로 변경
        DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime changeData = LocalDateTime.parse(date+hhmm, fomatter);
        
       
        return changeData;
    }

    @Override
    public LocalDate tLocalDate(LocalDateTime dateTime){
        LocalDate date = dateTime.toLocalDate();

        return date;
    }

    //글하나 불러오는 메소드
    @Override
    public GatherDTO get_Gather(Long g_id) {
        Gather gather =gatherMapper.get_Gather(g_id);
        GatherDTO gatherDTO = modelMapper.map(gather,GatherDTO.class);

        return gatherDTO;
    }


    @Override
    public int viewCount(Long g_id) {
        
        return gatherMapper.viewCount(g_id);
    }


    @Override
    public int userPointMinus(Long point, int u_id) {
        
        return gatherMapper.userPointMinus(point, u_id);
    }


    @Override
    public int userPointReset(Long point, int u_id) {
        
        return gatherMapper.userPointReset(point, u_id);
    }


    @Override
    public int gatherUpdate(GatherDTO gatherDTO) {
        Gather gather = modelMapper.map(gatherDTO, Gather.class);    

        return gatherMapper.gatherUpdate(gather);
    }


    @Override
    public Long gatherToUser(Long g_id) {
        
        return gatherMapper.gatherToUser(g_id);
    }
    
    //글 받아오는게 다달라서 매개변수 넣으면 매차쿠차 디티오 완성
    @Override
    public GatherDTO mergeDTO(String CategoryName,GatherDTO dto,int userId,String beforStartDate, String beforEndDate,Long g_id ){
        if(dto.getPoint()==null){
            dto.setPoint(0L);
        }
        dto.setCategory(categoryService.CategoryId(CategoryName));
        dto.setUser((long)userId);
        dto.setId(g_id);
        LocalDateTime date1 =toLocalDateTime(beforStartDate);
        LocalDateTime date2 =toLocalDateTime(beforEndDate);

        int result = date1.compareTo(date2);
        if(result>0){
            dto.setEndDate(date1);
            dto.setStartDate(date2);
        }else{
            dto.setEndDate(date2);
            dto.setStartDate(date1);
        }



        return dto;
    }


    @Override
    public int deleteResiter(Long g_id) {
        
        return gatherMapper.deleteResiter(g_id);
    }


    @Override
    public int overPoincheck(GatherDTO gatherDTO,UserDTO userDTO){
        int result =0;
        if(gatherDTO.getPoint()>userDTO.getPoint()){




            return result;
        }
        result=userPointMinus(gatherDTO.getPoint(), userDTO.getId());

        return result;
    }
    
    
    
}
