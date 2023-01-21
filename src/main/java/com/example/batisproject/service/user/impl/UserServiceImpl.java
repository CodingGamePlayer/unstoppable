package com.example.batisproject.service.user.impl;

import com.example.batisproject.dto.UserDTO;
import com.example.batisproject.entity.User;
import com.example.batisproject.mapper.UserMapper;
import com.example.batisproject.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public UserDTO getByEmailPassword(String email, String password) {
        return null;
    }

    @Override
    public int insert(UserDTO userDTO) {

        if(userDTO == null) {
            log.info("==================================");
            log.info("UserDTO 정보가 없습니다. 데이터 입력 여부를 확인 해주세요.");

            return 0;
        }
        User user = modelMapper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String now = String.valueOf(LocalDate.now());
        user.setRegdate(now);
//        user.setRegdate(LocalDate.now());
//        user.setRole("ROLE_USER");

        int result = userMapper.insert(user);
        log.info("==================================");
        log.info("result : " + result);

        if (result == 0) {
            log.info("==================================");
            log.info("DB에 저장되지 않았습니다.");
            return 0;
        }

        return 1;
    }


    @Override
    public UserDTO existsByEmail(String email) {
        return null;
    }

    @Override
    public UserDTO existsByNickName(String nickname) {
        return null;
    }
}
