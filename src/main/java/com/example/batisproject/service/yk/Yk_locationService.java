package com.example.batisproject.service.yk;

import com.example.batisproject.dto.LocationDTO;

import java.util.List;

public interface Yk_locationService {


    //로케이션 전체 리스트 받기
    public List<LocationDTO> getList();

    //로케이션 동 정보만 받기
    String getLocation_Dong(int l_id);
}
