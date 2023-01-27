package com.example.batisproject.service.gather;

import com.example.batisproject.entity.Gather;

import java.util.List;
import java.util.Optional;

public interface GatherService {

    List<Gather> getAll();

    List<Gather> getAllByNickname(String nickname);

    int create(Gather gather);

    List<Gather> getByCategory(Integer category);

    List<Gather> getByCategoryAndNickName(Integer category, String nickname);
}
