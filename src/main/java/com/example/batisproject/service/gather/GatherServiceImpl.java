package com.example.batisproject.service.gather;

import com.example.batisproject.dto.*;
import com.example.batisproject.entity.Gather;
import com.example.batisproject.entity.GatherResponse;
import com.example.batisproject.entity.User;
import com.example.batisproject.mapper.jungi.CategoryMapper;
import com.example.batisproject.mapper.jungi.GatherMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GatherServiceImpl implements GatherService {

    private final GatherMapper gatherMapper;

    private final CategoryMapper categoryMapper;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<GatherDTO> getAll() {
        return gatherMapper.getAll().stream()
                .map(gather -> modelMapper.map(gather, GatherDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<GatherDTO> getAllByLocation(Integer location) {
        return gatherMapper.getAllByLocation(location).stream().map(gather -> modelMapper.map(gather, GatherDTO.class)).collect(Collectors.toList());

    }

    @Override
    public List<GatherDTO> getAllByNickname(String nickname) {
        return gatherMapper.getAllByNickname(nickname).stream()
                .map(gather -> modelMapper.map(gather, GatherDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public int create(GatherDTO gatherDTO) {
        Gather gather = modelMapper.map(gatherDTO, Gather.class);
        int result = gatherMapper.insert(gather);
        log.info("insert gather Id : "+gather.getId());
        log.info("==================================");
        log.info("result : " + result);
        if (result == 0) {
            log.info("==================================");
            log.info("DB에 저장되지 않았습니다.");
            return 0;
        }
        return gather.getId().intValue();
    }

    @Override
    public List<GatherDTO> getByCategory(Integer category) {
        return gatherMapper.getByCategory(category).stream()
                .map(gather -> modelMapper.map(gather, GatherDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<GatherDTO> getByCategoryAndNickName(Integer category, String nickname) {
        return gatherMapper.getByCategoryAndNickname(category, nickname).stream()
                .map(gather -> modelMapper.map(gather, GatherDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PageResponseDTO<GatherResponseDTO> getAllMyList(PageRequestDTO pageRequestDTO) {

        List<GatherResponse> gathers = gatherMapper.selectMyList(pageRequestDTO);

        List<GatherResponseDTO> dtoList = gathers.stream()

                .map(gatherResponse -> modelMapper.map(gatherResponse, GatherResponseDTO.class))
                .collect(Collectors.toList());

        int total = gatherMapper.getMyListCount(pageRequestDTO);

        PageResponseDTO<GatherResponseDTO> pageResponseDTO = PageResponseDTO.<GatherResponseDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .total(total)
                .dtoList(dtoList)
                .build();

        return pageResponseDTO;
    }

    @Override
    public PageResponseDTO<GatherResponseDTO> getAllOtherList(PageRequestDTO pageRequestDTO) {

        List<GatherResponse> gathers = gatherMapper.selectOtherList(pageRequestDTO);
        List<GatherResponseDTO> dtoList = gathers.stream()

                .map(gatherResponse -> modelMapper.map(gatherResponse, GatherResponseDTO.class))
                .collect(Collectors.toList());

        int total = gatherMapper.getOtherListCount(pageRequestDTO);

        PageResponseDTO<GatherResponseDTO> pageResponseDTO = PageResponseDTO.<GatherResponseDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .total(total)
                .dtoList(dtoList)
                .build();

        return pageResponseDTO;
    }

    @Override
    public String getNameByCategory(Integer category) {
        return categoryMapper.getNamebyCategoryId(category);
    }


    @Autowired
    public GatherServiceImpl(GatherMapper gatherMapper, CategoryMapper categoryMapper) {
        this.gatherMapper = gatherMapper;
        this.categoryMapper = categoryMapper;
    }
}
