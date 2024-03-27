package com.learning.board_0326.controllers;

import com.learning.board_0326.services.BoardService;
import com.learning.board_0326.vos.ListVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Value("${login.kakao.sdk.code}")
    private String kakaoSDKCode;

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping({"/list/{page}", "/list"})
    public String getList(
            @PathVariable("page") Optional<Integer> optionalPage,
            Model model,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession(false);

        if (session.getAttribute("loginEmail") == null) {
            return "redirect:/user/login";
        } else {
            int page = optionalPage.orElse(1);
            ListVo vo = new ListVo(page);
            boardService.getArticles(vo);
            model.addAttribute("kakaoSDKCode", kakaoSDKCode);
            model.addAttribute("vo", vo);
            return "board/list";
        }

    }
}
