package com.learning.board_0326.mappers;

import com.learning.board_0326.dtos.ArticleDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface IBoardMapper {

    int selectArticleCount();

    ArrayList<ArticleDto> selectArticles(
            @Param("offset") int offset,
            @Param("limit") int limit
    );

    ArticleDto selectArticle(
            @Param("aid") int aid
    );

    void insertArticle(
            @Param("board_writer") String board_writer,
            @Param("board_title") String board_title,
            @Param("board_content") String board_content,
            @Param("board_password") String board_password
    );

    String selectBoardWriter(
            @Param("aid") int aid
    );

    void updateArticle(
            @Param("aid") int aid,
            @Param("board_title") String board_title,
            @Param("board_content") String board_content,
            @Param("board_writer") String boarD_writer
    );

    void deleteArticle(
            @Param("aid") int aid,
            @Param("board_writer") String board_writer
    );

    void insertDeleteLog(
            @Param("aid") int aid
    );

}
