package com.example.batisproject.service.gather;

import com.example.batisproject.dto.GatherDTO;
import com.example.batisproject.dto.PageRequestDTO;
import com.example.batisproject.dto.PageResponseDTO;
import com.example.batisproject.dto.UserDTO;
import com.example.batisproject.entity.Gather;
import com.example.batisproject.entity.User;
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


    private PageResponseDTO<GatherDTO> getGatherDTOPageResponseDTO(PageRequestDTO pageRequestDTO, List<Gather> gathers) {
        List<GatherDTO> dtoList = gathers.stream()

                .map(gather -> modelMapper.map(gather, GatherDTO.class))
                .collect(Collectors.toList());

        int total = gatherMapper.getCount(pageRequestDTO);

        PageResponseDTO<GatherDTO> pageResponseDTO = PageResponseDTO.<GatherDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .total(total)
                .dtoList(dtoList)
                .build();

        return pageResponseDTO;
    }

    @Override
    public PageResponseDTO<GatherDTO> getAllMyList(PageRequestDTO pageRequestDTO) {

        List<Gather> gathers = gatherMapper.selectMyList(pageRequestDTO);
        List<GatherDTO> dtoList = gathers.stream()

                .map(gather -> modelMapper.map(gather, GatherDTO.class))
                .collect(Collectors.toList());

        int total = gatherMapper.getMyListCount(pageRequestDTO);

        PageResponseDTO<GatherDTO> pageResponseDTO = PageResponseDTO.<GatherDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .total(total)
                .dtoList(dtoList)
                .build();

        return pageResponseDTO;
    }

    @Override
    public PageResponseDTO<GatherDTO> getAllOtherList(PageRequestDTO pageRequestDTO) {

        List<Gather> gathers = gatherMapper.selectOtherList(pageRequestDTO);
        List<GatherDTO> dtoList = gathers.stream()

                .map(gather -> modelMapper.map(gather, GatherDTO.class))
                .collect(Collectors.toList());

        int total = gatherMapper.getOtherListCount(pageRequestDTO);

        PageResponseDTO<GatherDTO> pageResponseDTO = PageResponseDTO.<GatherDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .total(total)
                .dtoList(dtoList)
                .build();

        return pageResponseDTO;
    }

    @Autowired
    public GatherServiceImpl(GatherMapper gatherMapper) {
        this.gatherMapper = gatherMapper;
    }
}
