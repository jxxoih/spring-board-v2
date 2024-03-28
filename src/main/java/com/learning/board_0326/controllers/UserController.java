package com.learning.board_0326.controllers;

import com.learning.board_0326.vos.UserVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    public class SessionConst {
        public static final String LOGIN_EMAIL = "loginEmail";
        public static final String ACCESS_TOKEN = "accessToken";
    }

    @Value("${login.kakao.sdk.code}")
    private String kakaoSDKCode;

    @GetMapping("/login")
    public String getLogin(
            Model model,
            HttpServletRequest request
    ) {

        HttpSession session = request.getSession(false);
        model.addAttribute("kakaoSDKCode", kakaoSDKCode);

        if (session == null || session.getAttribute("loginEmail") == null) {
            System.out.println("로그인페이지로이동");
            return "user/login";
        } else {
            System.out.println("로그인되어있음");
            return "redirect:/board/list";
        }
    }

    @RequestMapping(
            value = "/logout"
    )
    public String logout(
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            System.out.println(session.getAttribute("loginEmail"));
            System.out.println("세션있음?");
            session.invalidate();
        } else {
            System.out.println("세션없음?");
        }

        return "redirect:/user/login";
    }

    @ResponseBody
    @RequestMapping("/login/oauth/code/kakao")
    public String oauthCodeForKakao(
            UserVo vo,
            HttpServletRequest request
    ) {

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_EMAIL, vo.getEmail());
        session.setAttribute(SessionConst.ACCESS_TOKEN, vo.getAccessToken());

        return vo.getEmail();
    }
}
