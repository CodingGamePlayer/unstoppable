package com.example.batisproject.mapper.admin;

import com.example.batisproject.dto.PageRequestDTO;
import com.example.batisproject.entity.Gather;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminGatherMapper {

    List<Gather> selectAll(PageRequestDTO pageRequestDTO);

    int getCount(PageRequestDTO pageRequestDTO);
}
