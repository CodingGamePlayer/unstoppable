package com.example.batisproject.apicontroller.user;

import lombok.extern.slf4j.Slf4j;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Slf4j
@RestController
public class GatherApiController {

    @Value("${kr.co.unstoppable.upload.path}")
    private String uploadPath;

    @Operation(summary = "myGather Image View method", description = "내 모임 이미지를 가져오는 method")
    @GetMapping("/user/myGather/{fileName}")
    public ResponseEntity<Resource> viewMyGatherFile(@PathVariable String fileName) {

        return getResourceResponseEntity(fileName);
    }

    @Operation(summary = "gather Image View method", description = "모임 이미지를 가져오는 method")
    @GetMapping("/user/gather/{fileName}")
    public ResponseEntity<Resource> viewGatherFile(@PathVariable String fileName) {

        return getResourceResponseEntity(fileName);
    }

    private ResponseEntity<Resource> getResourceResponseEntity(@PathVariable String fileName) {
        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);

        String resourceName = resource.getFilename();
        HttpHeaders headers = new HttpHeaders();

        try {
            headers.add("Content-Type", Files.probeContentType( resource.getFile().toPath() ));
        } catch (IOException e){
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().headers(headers).body(resource);
    }

}
