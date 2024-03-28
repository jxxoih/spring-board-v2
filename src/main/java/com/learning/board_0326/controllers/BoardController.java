package com.learning.board_0326.controllers;

import com.learning.board_0326.dtos.UserDto;
import com.learning.board_0326.enums.CommonResult;
import com.learning.board_0326.services.BoardService;
import com.learning.board_0326.vos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
            HttpServletRequest request,
            UserDto userDto
    ) {

        String userEmail = boardService.getSessionUserEmail(request);
        if(userEmail == null) {
            return "redirect:/user/login";
        }

        userDto.setEmail(userEmail);
        int page = optionalPage.orElse(1);
        ListVo vo = new ListVo(page);
        boardService.getArticles(vo);
        model.addAttribute("kakaoSDKCode", kakaoSDKCode);
        model.addAttribute("vo", vo);
        return "board/list";
    }

    @GetMapping("/read/{aid}")
    public String getReadPage(
            @PathVariable("aid") int aid,
            Model model,
            HttpServletRequest request
    ) {
        // count 증가 추후 필요
        String userEmail = boardService.getSessionUserEmail(request);
        if(userEmail == null) {
            return "redirect:/user/login";
        }

        UserDto userDto = new UserDto(userEmail);

        ReadVo vo = new ReadVo(aid);
        vo.setUser(userDto);
        boardService.getArticle(vo);

        model.addAttribute("vo", vo);

        return "board/read";
    }

    @GetMapping("/write")
    public String getWritePage(
            HttpServletRequest request
    ) {
        String userEmail = boardService.getSessionUserEmail(request);
        if(userEmail == null) {
            return "redirect:/user/login";
        }

        return "board/write";
    }

    @PostMapping("/write")
    public String postWrite(
            HttpServletRequest request,
            WriteVo vo,
            Model model
    ) {
        String userEmail = boardService.getSessionUserEmail(request);
        if(userEmail == null) {
            return "redirect:/user/login";
        }

        vo.setBoard_writer(userEmail);
        boardService.writeArticle(vo);

        if(vo.getResult() == CommonResult.SUCCESS) {
            return "redirect:/board/list";
        } else {
            model.addAttribute("vo", vo);
            return "board/write";
        }
    }

    @GetMapping("/edit/{aid}")
    public String getEditPage(
            @PathVariable("aid") int aid,
            HttpServletRequest request,
            Model model
    ) {
        String userEmail = boardService.getSessionUserEmail(request);
        if(userEmail == null) {
            return "redirect:/user/login";
        }

        ReadVo vo = new ReadVo(aid);

        boardService.getArticle(vo);
        if(!userEmail.equals(vo.getArticle().getBoard_writer())) {
            return "redirect:/board/list";
        }

        model.addAttribute("vo", vo.getArticle());

        return "board/edit";
    }

    @PostMapping("/edit/{aid}")
    public String editArticle(
            @PathVariable("aid") int aid,
            HttpServletRequest request,
            Model model,
            EditVo vo
            ) {
        String userEmail = boardService.getSessionUserEmail(request);
        if(userEmail == null) {
            return "redirect:/user/login";
        }

        vo.setArticleId(aid);
        vo.setBoard_writer(userEmail);

        boardService.editArticle(vo);

        if(vo.getResult() == CommonResult.SUCCESS) {
            return "redirect:/board/read/" + aid;
        } else {
            model.addAttribute("vo", vo);
            return "board/edit";
        }
    }

    @RequestMapping("/delete/{aid}")
    public String deleteArticle(
            @PathVariable("aid") int aid,
            HttpServletRequest request
    ) {
        String userEmail = boardService.getSessionUserEmail(request);
        if(userEmail == null) {
            return "redirect:/user/login";
        }

        DeleteVo vo = new DeleteVo(aid);
        vo.setBoard_writer(userEmail);

        boardService.deleteArticle(vo);

        if(vo.getResult() == CommonResult.SUCCESS) {
            return "redirect:/board/list";
        } else {
            return "redirect:/board/read/" + aid;
        }
    }
}
