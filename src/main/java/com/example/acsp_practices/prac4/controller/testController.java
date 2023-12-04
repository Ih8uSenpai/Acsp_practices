package com.example.acsp_practices.prac4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {


    @GetMapping("/test")
    public String websPage(){
        return "sdfsdfsdf";
    }
}

