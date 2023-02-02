package com.example.batisproject.service.yk.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.batisproject.dto.LocationDTO;
import com.example.batisproject.mapper.yk.Yk_locationMapper;
import com.example.batisproject.service.yk.Yk_locationService;

@Service
public class Yk_locationServiceImpl implements Yk_locationService{

    @Autowired
    Yk_locationMapper locationMapper;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public List<LocationDTO> getList() {
        // return null;
        
        return locationMapper.getList().stream()
        .map(location->modelMapper.map(location, LocationDTO.class))
        .collect(Collectors.toList());
    }
    
}
