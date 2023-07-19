package com.fooddelivery.controller;

import com.fooddelivery.parser.Parser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/parse")
public class ParseController {

    @Resource
    private Parser parser;

    @GetMapping
//    @PostMapping
    public String parse() {
        parser.parse();
        return "Ready!";
    }
}
