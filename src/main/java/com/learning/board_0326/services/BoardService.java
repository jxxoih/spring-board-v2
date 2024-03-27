package com.learning.board_0326.services;

import com.learning.board_0326.dtos.ArticleDto;
import com.learning.board_0326.mappers.IBoardMapper;
import com.learning.board_0326.vos.ListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
