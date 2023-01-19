package com.example.batisproject.apicontroller.user;

import com.example.batisproject.dto.UserDTO;
import com.example.batisproject.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserApiController {

    @Autowired
    private UserServiceImpl userService;

//    @PostMapping("/api/user/signup")
//    public ResponseEntity<UserDTO> signup(@RequestBody UserDTO userDTO) {
//
//        log.info("UserDTO : " + userDTO.toString());
//        int result = userService.insert(userDTO);
//        if (result == 0) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
}
