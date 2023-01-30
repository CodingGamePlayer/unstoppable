package com.example.batisproject.mapper.admin;


import com.example.batisproject.dto.GatherDTO;
import com.example.batisproject.dto.PageRequestDTO;
import com.example.batisproject.entity.Gather;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface AdminGatherMapper {

    List<Gather> selectAll(PageRequestDTO pageRequestDTO);

    int getCount(PageRequestDTO pageRequestDTO);

    @Delete("DELETE FROM gather WHERE g_id = #{gatherDTO.id}")
    int delete(@Param("gatherDTO")GatherDTO gatherDTO);

}
