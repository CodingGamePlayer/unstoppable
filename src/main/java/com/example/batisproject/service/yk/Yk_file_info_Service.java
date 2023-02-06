package com.example.batisproject.service.yk;



import org.springframework.web.multipart.MultipartFile;

import com.example.batisproject.dto.FileInfoDTO;
import com.example.batisproject.dto.GatherImageDTO;
import com.example.batisproject.entity.FileInfo;

public interface Yk_file_info_Service {
    
    Long inputImg(MultipartFile file);

    public int registerGather_img(GatherImageDTO imgDTO);

    
    public FileInfoDTO getFileInfo(Long g_id);

}
