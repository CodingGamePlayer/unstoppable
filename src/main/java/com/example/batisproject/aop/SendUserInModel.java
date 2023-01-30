package com.example.batisproject.aop;

import com.example.batisproject.controller.AuthenticationForModel;
import com.example.batisproject.dto.UserDTO;
import com.example.batisproject.entity.User;
import com.example.batisproject.service.user.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Collection;
import java.util.Map;

@Aspect
@Component
public class SendUserInModel {

    @Autowired
    private AuthenticationForModel authenticationForModel;

    @Autowired
    private UserServiceImpl userService;


    @Pointcut("execution(* com.example.batisproject.controller.user.UserController.*(..))")
    private void cut() {}

    @Before("cut()")
    public void inputModel(){



    }
}
