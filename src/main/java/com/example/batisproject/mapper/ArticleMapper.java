package com.example.batisproject.mapper;

import com.example.batisproject.entity.Article;
import com.example.batisproject.dto.PageRequestDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {

    @Insert("INSERT INTO article (art_title, art_body, art_regdate, art_moddate, userid) " +
            "VALUES (#{article.title}, #{article.body}, #{article.regdate}, #{article.moddate}, #{article.userid})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int create(@Param("article")Article article);

    @Select("SELECT art_id, art_title, art_body, art_regdate, art_moddate, userid FROM article " +
            "ORDER BY art_id DESC " +
            "LIMIT #{pageRequestDTO.skip}, #{pageRequestDTO.size}")
    @Results(id = "ArticleMap", value = {
            @Result(property = "id", column = "art_id"),
            @Result(property = "title", column = "art_title"),
            @Result(property = "body", column = "art_body"),
            @Result(property = "regdate", column = "art_regdate"),
            @Result(property = "moddate", column = "art_moddate"),
            @Result(property = "userid", column = "userid"),

    })
    List<Article> selectAll(@Param("pageRequestDTO") PageRequestDTO pageRequestDTO);

    @Select("SELECT count(art_id) from article")
    int getCount();

    @Select("SELECT * FROM article WHERE art_id = #{id}")
    @ResultMap("ArticleMap")
    Article selectOne(int id);

    @Update("UPDATE article " +
            "SET " +
            "art_title = #{article.title}, " +
            "art_body = #{article.body}, " +
            "art_moddate = #{article.moddate}, " +
            "userid = #{article.userid} " +
            "WHERE art_id = #{article.id}")
    int update(@Param("article")Article article);

    @Delete("DELETE FROM article " +
            "WHERE art_id = #{id}")
    int delete(int id);


}
