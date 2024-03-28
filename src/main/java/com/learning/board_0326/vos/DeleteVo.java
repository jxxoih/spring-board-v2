package com.learning.board_0326.vos;

import com.learning.board_0326.enums.CommonResult;

public class DeleteVo {
    private final int articleId;

    private String board_writer;

    private CommonResult result;

    public DeleteVo(int articleId) {
        this.articleId = articleId;
    }

    public int getArticleId() {
        return articleId;
    }

    public String getBoard_writer() {
        return board_writer;
    }

    public void setBoard_writer(String board_writer) {
        this.board_writer = board_writer;
    }

    public CommonResult getResult() {
        return result;
    }

    public void setResult(CommonResult result) {
        this.result = result;
    }
}
