package com.gyana.Gyana_Social.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Gyana Social is running";
    }

    @GetMapping("/home2")
    public String home2() {
        return "This is home controller 2";
    }

    @GetMapping("/CodeWithGyana")
    public String home3() {
        return "Gyananjay learning springboot";
    }
}
