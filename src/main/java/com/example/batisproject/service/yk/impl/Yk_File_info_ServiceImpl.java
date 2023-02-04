package com.example.batisproject.service.yk.impl;

import java.io.File;

import java.io.IOException;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.batisproject.entity.FileInfo;
import com.example.batisproject.mapper.Yk_fileInfoMapper;
import com.example.batisproject.service.yk.Yk_file_info_Service;

@Service
public class Yk_File_info_ServiceImpl implements Yk_file_info_Service {
    
    //서버 파일패스 앱포르퍼티로 설정 일단 안되는듯 지우던가 재확인
    @Value("${file.path}")
    private String savePath;

    @Autowired
    Yk_fileInfoMapper fileInfoMapper;

    //각자 상대경로(로컬)에 저장되는 주소로 갖기위해 설정
    private String rootPath = System.getProperty("user.dir");
    private String fileDir = "/src/main/resources/static/assets/img/upload_img_file";

    

    @Override
    public int inputImg(MultipartFile file) {
        System.out.println("서비스 진입");

        String save_path = rootPath+fileDir;
        System.out.println(save_path);
        
        
        String contentType_name = file.getContentType();
        System.out.println(contentType_name);
        String[] afer_contentType_name = contentType_name.split("/");
        
        String fileName = file.getOriginalFilename();
        String contentType = afer_contentType_name[1];
        String saveFileName = UUID.randomUUID().toString()+"."+contentType;
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
        
        File savefile = new File(save_path, saveFileName);

        try {
            file.transferTo(savefile);
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        // System.out.println("세이브파일 저장후 "+savefile);
        return 1;

    }
    
    
}
