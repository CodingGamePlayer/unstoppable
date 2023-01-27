package com.example.batisproject.service.gather;

import com.example.batisproject.entity.Gather;
import com.example.batisproject.mapper.jungi.GatherMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class GatherServiceImpl implements GatherService {

    private final GatherMapper gatherMapper;
    @Override
    public List<Gather> getAll() {
        return gatherMapper.getAll();
    }

    @Override
    public List<Gather> getAllByNickname(String nickname) {
        return gatherMapper.getAllByNickname(nickname);
    }

    @Override
    public int create(Gather gather) {
        return gatherMapper.insert(gather);
    }

    @Override
    public List<Gather> getByCategory(Integer category) {
        return gatherMapper.getByCategory(category);
    }

    @Override
    public List<Gather> getByCategoryAndNickName(Integer category, String nickname) {
        return gatherMapper.getByCategoryAndNickname(category, nickname);
    }

    @Autowired
    public GatherServiceImpl(GatherMapper gatherMapper) {
        this.gatherMapper = gatherMapper;
    }
}
