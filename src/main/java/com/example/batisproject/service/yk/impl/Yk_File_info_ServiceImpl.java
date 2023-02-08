package com.example.batisproject.service.yk.impl;

import java.io.File;


import java.io.IOException;






import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.batisproject.dto.FileInfoDTO;
import com.example.batisproject.dto.GatherImageDTO;
import com.example.batisproject.entity.FileInfo;
import com.example.batisproject.entity.Gather;
import com.example.batisproject.entity.GatherImage;
import com.example.batisproject.mapper.Yk_fileInfoMapper;
import com.example.batisproject.service.yk.Yk_file_info_Service;

@Service
public class Yk_File_info_ServiceImpl implements Yk_file_info_Service {
    
    //서버 파일패스 앱포르퍼티로 설정 일단 안되는듯 지우던가 재확인
    @Value("${file.path}")
    private String savePath;

    @Autowired
    Yk_fileInfoMapper fileInfoMapper;

    @Autowired
    ModelMapper modelMapper;

    //각자 상대경로(로컬)에 저장되는 주소로 갖기위해 설정 각자의 컴퓨터 앞부분만 따짐
    private String rootPath = System.getProperty("user.dir");
    //나머지 프로젝트 경로
    private String fileDir = "/src/main/resources/static/assets/img/upload_img_file";
    private String save_path = rootPath+fileDir;


    
    //이미지 정보 저장
    @Override
    public Long inputImg(MultipartFile file,Long g_id) {

        System.out.println(save_path);
        
        //image/png 이런식으로 나옴
        String contentType_name = file.getContentType();
        System.out.println(contentType_name);
        
        
        
        // 뒤에 파일형식만 받게 뒷부분만 잘라줌
        String[] afer_contentType_name = contentType_name.split("/");
        
        String fileName = file.getOriginalFilename();
        String contentType = afer_contentType_name[1];
        String saveFileName = UUID.randomUUID().toString()+"."+contentType;
        System.out.println("이미지 uuid "+saveFileName+ "파일이름 잘나오나" +fileName +"컨탠츠 패스 확인" +contentType);

        if(contentType.equals("octet-stream")){
            System.out.println("---------------octet로 비교로 잘됨");
            return 0L;
        }
        
        FileInfo setToFileInfo = FileInfo.builder()
            .fileName(fileName)
            .saveFileName(saveFileName)
            .contentType(contentType)
            .build();


        int result=fileInfoMapper.putImgInfo(setToFileInfo);
        if(result<0){
            //정보 저장 실패시
            return setToFileInfo.getId();
        }

        //객체 파일타입의 new(경로,파일이름)
        File savefile = new File(save_path, saveFileName);

        try {
            
            //멀티파트파일 타입의 프랜스포 실직적으로 변환해서 로컬에 저장됨
            file.transferTo(savefile);
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // System.out.println("세이브파일 저장후 "+savefile);
        // 글과 이미지 연관관계 테이블 디비 저장하기
        GatherImageDTO imgDTO = GatherImageDTO.builder()
        .fileInfo(setToFileInfo.getId())
        .gather(g_id)
        .build();
        //파일 연관간계 저장
        result = registerGather_img(imgDTO);
        if(result<0){
            return 0L;
        }
        
        
        System.out.println("사진 작성 성공");
        return setToFileInfo.getId();

    }


    
    //이미지 + 글 연관관계 저장 메소드
    public int registerGather_img(GatherImageDTO imgDTO){

         GatherImage img = modelMapper.map(imgDTO, GatherImage.class);
    
         return fileInfoMapper.registerGather_img(img);
     }
    
    //이미지 정보 불러오기
    public FileInfoDTO getFileInfo(Long g_id){
        FileInfo fileInfo = fileInfoMapper.getFileInfo(g_id);
        FileInfoDTO chackNullDTO = new FileInfoDTO();
        try {
                if(!fileInfo.equals(null)){
                    FileInfoDTO fileInfoDTO = modelMapper.map(fileInfo, FileInfoDTO.class);
                    return fileInfoDTO;
                }    
            } catch (Exception e) {
                return chackNullDTO;
                
            }
        
        return chackNullDTO;
    }
    



}
