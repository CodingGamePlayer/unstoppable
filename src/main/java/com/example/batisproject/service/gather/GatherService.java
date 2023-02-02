package com.example.batisproject.service.gather;

import com.example.batisproject.dto.GatherDTO;
import com.example.batisproject.dto.PageRequestDTO;
import com.example.batisproject.dto.PageResponseDTO;
import com.example.batisproject.entity.Gather;

import java.util.List;
import java.util.Optional;

public interface GatherService {

    List<GatherDTO> getAll();

    List<GatherDTO> getAllByLocation(Integer location);

    List<GatherDTO> getAllByNickname(String nickname);

    int create(GatherDTO gatherDTO);

    List<GatherDTO> getByCategory(Integer category);

    List<GatherDTO> getByCategoryAndNickName(Integer category, String nickname);

    PageResponseDTO<GatherDTO> getAllMyList(PageRequestDTO pageRequestDTO);
    PageResponseDTO<GatherDTO> getAllOtherList(PageRequestDTO pageRequestDTO);

}
