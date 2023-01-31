package com.example.batisproject.service.gather;

import com.example.batisproject.dto.GatherDTO;
import com.example.batisproject.entity.Gather;

import java.util.List;
import java.util.Optional;

public interface GatherService {


    int create(GatherDTO gatherDTO);

    List<GatherDTO> getAll();
    List<GatherDTO> getAllByLocation(Integer location);
    List<GatherDTO> getAllByNickname(String nickname);
    List<GatherDTO> getByCategory(Integer category);
    List<GatherDTO> getByCategoryAndNickName(Integer category, String nickname);

    List<GatherDTO> getAllMyList(Integer category, String nickname, int location);
    List<GatherDTO> getAllMyList(String nickname, int location);
    List<GatherDTO> getAllOtherList(Integer category, String nickname, int location);
    List<GatherDTO> getAllOtherList(String nickname, int location);
}
