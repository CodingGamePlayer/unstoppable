package com.example.batisproject.service.location;

import com.example.batisproject.dto.LocationDTO;


import java.util.List;

public interface LocationService {

    List<LocationDTO> getAll();

    LocationDTO getByUsername(String username);
}
