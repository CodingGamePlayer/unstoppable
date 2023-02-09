package com.example.batisproject.service.yk.impl;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.batisproject.dto.GatherDTO;
import com.example.batisproject.entity.Gather;
import com.example.batisproject.mapper.yk.Yk_gatherMapper;
import com.example.batisproject.service.yk.Yk_gatherService;




@Service
public class Yk_gatherServiceImpl implements Yk_gatherService{

    @Autowired
    private Yk_gatherMapper gatherMapper;

    @Autowired
    private ModelMapper modelMapper;



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
        System.out.println(date+hhmm);                                                        
        //데이터타입 스트링에서 로컬데이타로 변경
        DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime changeData = LocalDateTime.parse(date+hhmm, fomatter);
        
       
        return changeData;
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
    
    
    
    
    
    
}
