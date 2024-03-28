package com.learning.board_0326.vos;

import com.learning.board_0326.enums.CommonResult;

public class EditVo {

    private int articleId;

    private String board_writer;
    private String board_title;
    private String board_content;

    private String board_password;

    private CommonResult result;

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getBoard_writer() {
        return board_writer;
    }

    public void setBoard_writer(String board_writer) {
        this.board_writer = board_writer;
    }

    public String getBoard_title() {
        return board_title;
    }

    public void setBoard_title(String board_title) {
        this.board_title = board_title;
    }

    public String getBoard_content() {
        return board_content;
    }

    public void setBoard_content(String board_content) {
        this.board_content = board_content;
    }

    public String getBoard_password() {
        return board_password;
    }

    public void setBoard_password(String board_password) {
        this.board_password = board_password;
    }

    public CommonResult getResult() {
        return result;
    }

    public void setResult(CommonResult result) {
        this.result = result;
    }
}
