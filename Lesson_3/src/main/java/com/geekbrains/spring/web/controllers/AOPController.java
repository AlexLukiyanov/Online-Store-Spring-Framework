package com.geekbrains.spring.web.controllers;

import com.geekbrains.spring.web.configs.AppLoggingAspect;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/statistic")
@RequiredArgsConstructor
public class AOPController {

    private AppLoggingAspect appLoggingAspect;

    @GetMapping
    public String getStatistics() {


        //appLoggingAspect.methodForOrderStatistics();
        //appLoggingAspect.methodForUserStatistics();
    }
}
