package com.learning.board_0326.dtos;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ArticleDto {
    private static class Format {
        public static final String TIMESTAMP = "yyyy-MM-dd HH:mm";
    }

    private final int board_id;
    private final String board_title;
    private final String board_content;
    private final String board_writer;
    private final String board_password;
    private final int view_count;
    private final int use_state;
    private final Date created_at;
    private final String formattedTimestamp;

    public ArticleDto(int board_id, String board_title, String board_content, String board_writer, String board_password, int view_count, int use_state, Date created_at) {
        this.board_id = board_id;
        this.board_title = board_title;
        this.board_content = board_content;
        this.board_writer = board_writer;
        this.board_password = board_password;
        this.view_count = view_count;
        this.use_state = use_state;
        this.created_at = created_at;
        this.formattedTimestamp = new SimpleDateFormat(Format.TIMESTAMP).format(created_at);
    }

    public int getBoard_id() {
        return board_id;
    }

    public String getBoard_title() {
        return board_title;
    }

    public String getBoard_content() {
        return board_content;
    }

    public String getBoard_writer() {
        return board_writer;
    }

    public String getBoard_password() {
        return board_password;
    }

    public int getView_count() {
        return view_count;
    }

    public int getUse_state() {
        return use_state;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public String getFormattedTimestamp() {
        return formattedTimestamp;
    }
}
