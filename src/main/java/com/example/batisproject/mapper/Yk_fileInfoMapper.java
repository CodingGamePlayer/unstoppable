package com.example.batisproject.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.batisproject.entity.FileInfo;

@Mapper
public interface Yk_fileInfoMapper {

    @Insert("insert into file_info (file_name,save_file_name,content_type,delete_flag) values(#{fileName},#{saveFileName},#{contentType},0)")
    int putImgInfo(FileInfo fileEntity);


}
