package com.example.batisproject.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.example.batisproject.entity.FileInfo;
import com.example.batisproject.entity.GatherComment;
import com.example.batisproject.entity.GatherImage;

@Mapper
public interface Yk_fileInfoMapper {

    @Insert("insert into file_info (file_name,save_file_name,content_type,delete_flag) values(#{fileName},#{saveFileName},#{contentType},0)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int putImgInfo(FileInfo fileEntity);




    @Insert("insert into gather_image (f_id,g_id) values (#{fileInfo},#{gather});")
    int registerGather_img(GatherImage image);

    @Select("select * from file_info where f_id = (select f_id from gather_image where g_id = #{g_id});")
    FileInfo getFileInfo(Long g_id);

}
