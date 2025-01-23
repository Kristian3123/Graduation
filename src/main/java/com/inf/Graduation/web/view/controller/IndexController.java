package com.inf.Graduation.web.view.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@AllArgsConstructor
//@RequestMapping("/")
@RequiredArgsConstructor
public class IndexController {

    @GetMapping("/")
    public String getIndex(Model model) {
        final String welcomeMessage = "Welcome to the Graduation Management System!";
        model.addAttribute("welcome", welcomeMessage);
        return "index";
    }

    @GetMapping("/unauthorized")
    public String getUnauthorized(Model model) {
        model.addAttribute("message", "You are not authorized to be here!");
        return "/errors/unauthorized-errors";
    }

}
