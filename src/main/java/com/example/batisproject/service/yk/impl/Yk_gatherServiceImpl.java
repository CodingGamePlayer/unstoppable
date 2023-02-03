package com.example.batisproject.service.yk.impl;


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
    public int gatherRegister(GatherDTO gatherDTO) {
        
        Gather gather = modelMapper.map(gatherDTO, Gather.class);

        return gatherMapper.gatherRegister(gather);
    }
    
    
        
    
    
    
}
