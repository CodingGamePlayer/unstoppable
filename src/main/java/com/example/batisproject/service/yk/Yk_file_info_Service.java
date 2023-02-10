package com.example.batisproject.service.yk;



import org.springframework.web.multipart.MultipartFile;

import com.example.batisproject.dto.FileInfoDTO;
import com.example.batisproject.dto.GatherImageDTO;
import com.example.batisproject.entity.FileInfo;

public interface Yk_file_info_Service {
    
    Long inputImg(MultipartFile file, Long g_id);

    public int registerGather_img(GatherImageDTO imgDTO);

    public FileInfoDTO getFileInfo(Long g_id);


     Long inputImgOrDelete(MultipartFile file,Long g_id);


     //파일 삭제시 메퍼 3개 불러야해서 삭제 메소드
     int deleteFileImg(Long g_id);

}
