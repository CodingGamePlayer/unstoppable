package com.example.batisproject.service.yk.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.example.batisproject.service.yk.Yk_locationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import com.example.batisproject.dto.LocationDTO;
import com.example.batisproject.mapper.yk.Yk_locationMapper;

@Service
public class Yk_locationServiceImpl implements Yk_locationService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    Yk_locationMapper locationMapper;

    @Override
    public List<LocationDTO> getList() {
       

        return locationMapper.getList().stream()
        .map(location->modelMapper.map(location, LocationDTO.class))
        .collect(Collectors.toList());
    }

    @Override
    public String getLocation_Dong(int l_id){
        

        return locationMapper.getLocation_Dong(l_id);
    };
    
}
