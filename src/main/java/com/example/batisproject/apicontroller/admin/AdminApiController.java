package com.example.batisproject.apicontroller.admin;

import com.example.batisproject.dto.UserDTO;
import com.example.batisproject.service.admin.impl.AdminServiceImpl;
import groovy.util.logging.Log4j2;
import groovy.util.logging.Slf4j;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@Tag(name = "Admin 권한을 가진 유저가 사용하는 controller",
        description = "isAuthorized 권한을 가진 유저들을 관리함.")
@RestController
public class AdminApiController {


    @Autowired
    private AdminServiceImpl adminService;

    @Operation(summary = "Role Change method", description = "유저의 권한을 변경하는 method")
    @PutMapping(value = "/api/admin/user-role", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> changeRole(@RequestBody UserDTO userDTO){

        System.out.println("userDto : " + userDTO.toString());

        if (userDTO == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        int updated = adminService.update(userDTO);

        if(updated == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
