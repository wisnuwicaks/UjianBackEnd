package com.ujianbackend.movie.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {
    @GetMapping("/hallo")
    public String hallo(){
        return "hallo";
    }
}
