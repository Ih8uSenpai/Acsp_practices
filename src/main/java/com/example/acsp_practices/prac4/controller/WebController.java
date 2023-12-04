package com.example.acsp_practices.prac4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class WebController {

    @GetMapping("/prac4")
    public ModelAndView websPage() {
        return new ModelAndView("webs.html");
    }
}
