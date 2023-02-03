package com.example.batisproject.service.yk.impl;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.batisproject.entity.FileInfo;
import com.example.batisproject.mapper.Yk_fileInfoMapper;
import com.example.batisproject.service.yk.Yk_gatherService;

@Service
public class Yk_gatherServiceImpl implements Yk_gatherService {
    
    @Value("${file.path}")
    private String savePath;

    @Autowired
    Yk_fileInfoMapper fileInfoMapper;


    @Override
    public int inputImg(MultipartFile file) {
        System.out.println("서비스 진입");
        System.out.println(savePath);
        
        String contentType_name = file.getContentType();
        System.out.println(contentType_name);
        String[] afer_contentType_name = contentType_name.split("/");
        

        String fileName = afer_contentType_name[0];
        String contentType = afer_contentType_name[1];
        String saveFileName = UUID.randomUUID().toString();
        System.out.println("이미지 uuid "+saveFileName+ "파일이름 잘나오나" +fileName +"컨탠츠 패스 확인" +contentType);


        //디비에 저장하기
        FileInfo fileInfo = FileInfo.builder()
            .fileName(fileName)
            .saveFileName(saveFileName)
            .contentType(contentType)
            .build();

            int result=fileInfoMapper.putImgInfo(fileInfo);
        if(result<0){
            return 0;
        }
        File savefile = new File(savePath, saveFileName);
        System.out.println("세이브파일 저장후 "+savefile);
        return 1;

    }
    
    
}
