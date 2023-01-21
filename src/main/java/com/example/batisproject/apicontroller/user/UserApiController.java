package com.example.batisproject.apicontroller.user;

import com.example.batisproject.dto.UserDTO;
import com.example.batisproject.service.user.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "인증을 담당하는 controller",
        description = "Anonymous 권한을 가진 유저들이 인증 단계를 담당함.")
@RestController
public class UserApiController {

    @Autowired
    private UserServiceImpl userService;

    @Operation(summary = "Login method", description = "Login을 담당하는 method")
    @PostMapping(value = "/api/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO) {

        log.info("UserDTO : " + userDTO.toString());
        int result = userService.insert(userDTO);
        if (result == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Signup method", description = "회원가입을 담당하는 method")
    @PostMapping(value = "/api/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> signup(@RequestBody UserDTO userDTO){
        int insert = userService.insert(userDTO);

        if(insert == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
