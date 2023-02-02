package com.example.batisproject.mapper.jungi;

import com.example.batisproject.entity.Gather;
import com.example.batisproject.entity.GatherComment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GatherCommentMapper {

    @Select("select * from gather_comment")
    @Results(id = "gatherCommentMap", value = {
            @Result(property = "id", column = "gc_id"),
            @Result(property = "gather", column = "g_id"),
            @Result(property = "user", column = "u_id"),
            @Result(property = "role", column = "role")
    })
    List<GatherComment> getAll();

    @Insert("INSERT INTO gather_comment (g_id, u_id, role) values (#{gatherComment.gather}, #{gatherComment.user}, #{gatherComment.role})")
    @Options(useGeneratedKeys = true, keyProperty = "id") // 입력할 때 생성된 id를 바로 반환함.
    int insert(@Param("gatherComment") GatherComment gatherComment);
}
