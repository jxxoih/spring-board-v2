package com.learning.board_0326.vos;

import com.learning.board_0326.dtos.ArticleDto;

import java.util.ArrayList;

public class ListVo {
    private final int page;

    private int leftPage;
    private int rightPage;
    private int maxPage;

    private ArrayList<ArticleDto> articles;

    public ListVo(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public int getLeftPage() {
        return leftPage;
    }

    public void setLeftPage(int leftPage) {
        this.leftPage = leftPage;
    }

    public int getRightPage() {
        return rightPage;
    }

    public void setRightPage(int rightPage) {
        this.rightPage = rightPage;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public ArrayList<ArticleDto> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<ArticleDto> articles) {
        this.articles = articles;
    }
}
