package com.example.batisproject.service.location;

import com.example.batisproject.dto.LocationDTO;
import com.example.batisproject.entity.Location;
import com.example.batisproject.mapper.jungi.LocationMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LocationServiceImpl implements LocationService {

    private final LocationMapper locationMapper;
    private ModelMapper modelMapper = new ModelMapper();
    @Override
    public LocationDTO getByUsername(String username) {
        Location location = locationMapper.getByUsername(username);
        return modelMapper.map(location, LocationDTO.class);
    }

    @Autowired
    public LocationServiceImpl(LocationMapper locationMapper) {
        this.locationMapper = locationMapper;
    }
}
