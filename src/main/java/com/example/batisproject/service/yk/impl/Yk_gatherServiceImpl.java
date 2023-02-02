package com.example.batisproject.service.yk.impl;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.batisproject.service.yk.Yk_gatherService;

@Service
public class Yk_gatherServiceImpl implements Yk_gatherService {

    @Override
    public void inputImg(MultipartFile file) {
        String savePath = System.getProperty("user.dir")+"/src/main/resources/static/assets/img/yk_img_file";
        String saveFileName = UUID.randomUUID().toString();
        
        File savefile = new File(savePath, saveFileName);
        // UUID saveFileName = UUID.randomUUID();
        
    }
    
    
}
