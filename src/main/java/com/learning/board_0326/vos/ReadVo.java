package com.learning.board_0326.vos;

import com.learning.board_0326.dtos.ArticleDto;
import com.learning.board_0326.dtos.UserDto;
import com.learning.board_0326.enums.CommonResult;

public class ReadVo {
    private final int articleId;

    private ArticleDto article;
    private UserDto user;

    private CommonResult result;

    public ReadVo(int articleId) {
        this.articleId = articleId;
    }

    public int getArticleId() {
        return articleId;
    }

    public ArticleDto getArticle() {
        return article;
    }

    public void setArticle(ArticleDto article) {
        this.article = article;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public CommonResult getResult() {
        return result;
    }

    public void setResult(CommonResult result) {
        this.result = result;
    }
}
