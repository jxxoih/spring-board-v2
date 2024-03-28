package com.learning.board_0326.services;

import com.learning.board_0326.dtos.ArticleDto;
import com.learning.board_0326.enums.CommonResult;
import com.learning.board_0326.mappers.IBoardMapper;
import com.learning.board_0326.vos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Service
public class BoardService {
    private static final int ARTICLES_PER_PAGE = 10;
    private static final int PAGE_RANGE = 2;

    private final IBoardMapper boardMapper;

    @Autowired
    public BoardService(IBoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }


    public String getSessionUserEmail(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loginEmail") == null) {
            return null;
        }

        return session.getAttribute("loginEmail").toString();
    }




    public void getArticles(ListVo vo) {
        ArrayList<ArticleDto> articles = boardMapper.selectArticles(
                BoardService.ARTICLES_PER_PAGE * (vo.getPage() - 1),
                BoardService.ARTICLES_PER_PAGE
        );

        int articleCount = boardMapper.selectArticleCount();

        int maxPage = articleCount / BoardService.ARTICLES_PER_PAGE + (articleCount % BoardService.ARTICLES_PER_PAGE == 0 ? 0 : 1);
        int leftPage = Math.max(1, vo.getPage() - BoardService.PAGE_RANGE);
        int rightPage = Math.min(maxPage, vo.getPage() + BoardService.PAGE_RANGE);
        vo.setMaxPage(maxPage);
        vo.setLeftPage(leftPage);
        vo.setRightPage(rightPage);

        vo.setArticles(articles);
    }

    public void getArticle(ReadVo vo) {
//        조회수 증가 쿼리 필요

        ArticleDto article = boardMapper.selectArticle(vo.getArticleId());
        vo.setArticle(article);
    }

    public void writeArticle(WriteVo vo) {

        if(vo.getBoard_writer() == null|| vo.getBoard_writer().isEmpty() || vo.getBoard_writer() == "") {
            vo.setResult(CommonResult.NOT_AUTHORIZED);
            return;
        }

        boardMapper.insertArticle(
                vo.getBoard_writer(),
                vo.getBoard_title(),
                vo.getBoard_content(),
                ""
        );

        vo.setResult(CommonResult.SUCCESS);
    }

    public void editArticle(EditVo vo) {

        if(vo.getBoard_writer().isEmpty()
                || vo.getBoard_writer().equals("")
                || !vo.getBoard_writer().equals(boardMapper.selectBoardWriter(vo.getArticleId()))) {
            vo.setResult(CommonResult.NOT_AUTHORIZED);

            return;
        }

        boardMapper.updateArticle(
                vo.getArticleId(),
                vo.getBoard_title(),
                vo.getBoard_content(),
                vo.getBoard_writer()
        );

        vo.setResult(CommonResult.SUCCESS);
    }

    @Transactional
    public void deleteArticle(DeleteVo vo) {
        if(vo.getBoard_writer().isEmpty()
                || vo.getBoard_writer().equals("")
                || !vo.getBoard_writer().equals(boardMapper.selectBoardWriter(vo.getArticleId()))) {
            vo.setResult(CommonResult.NOT_AUTHORIZED);

            return;
        }

        boardMapper.deleteArticle(vo.getArticleId(), vo.getBoard_writer());
        boardMapper.insertDeleteLog(vo.getArticleId());

        vo.setResult(CommonResult.SUCCESS);


    }

}
