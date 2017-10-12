package com.eden.agent.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Create by zhaoxianghui on 2017/10/12.
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String login(HttpServletRequest request, HttpServletResponse response){
        return "redirect:/static/views/home.html";
    }
}
