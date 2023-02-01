package com.example.batisproject.service.gather;

import com.example.batisproject.dto.GatherDTO;
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
        log.info("==================================");
        log.info("result : " + result);
        if (result == 0) {
            log.info("==================================");
            log.info("DB에 저장되지 않았습니다.");
            return 0;
        }
        return result;
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
    public List<GatherDTO> getAllMyList(Integer category, String nickname, int location) {

        return gatherMapper.getAllMyListByCategoryNicknameLocation(category, nickname, location).stream()

                .map(gather -> modelMapper.map(gather, GatherDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<GatherDTO> getAllMyList(String nickname, int location) {
        return gatherMapper.getAllMyList(nickname, location).stream()
                .map(gather -> modelMapper.map(gather, GatherDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public List<GatherDTO> getAllOtherList(Integer category, String nickname, int location) {

        return gatherMapper.getAllOtherListByCategoryNicknameLocation(category, nickname, location).stream()

                .map(gather -> modelMapper.map(gather, GatherDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public List<GatherDTO> getAllOtherList(String nickname, int location) {
        return gatherMapper.getAllOtherList(nickname, location).stream()
                .map(gather -> modelMapper.map(gather, GatherDTO.class))
                .collect(Collectors.toList());
    }

    @Autowired
    public GatherServiceImpl(GatherMapper gatherMapper) {
        this.gatherMapper = gatherMapper;
    }
}
