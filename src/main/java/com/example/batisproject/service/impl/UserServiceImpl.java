package com.example.batisproject.service.impl;

import com.example.batisproject.domain.User;
import com.example.batisproject.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getByEmailPassword(String email, String password) {
        return null;
    }

    @Override
    public int insert(User user) {
        return 0;
    }

    @Override
    public User existsByEmail(String email) {
        return null;
    }

    @Override
    public User existsByNickName(String nickname) {
        return null;
    }
}
