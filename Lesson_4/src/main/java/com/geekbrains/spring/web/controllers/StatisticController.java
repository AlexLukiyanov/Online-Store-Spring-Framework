package com.geekbrains.spring.web.controllers;

import com.geekbrains.spring.web.configs.Statistic;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api/v1/statistic")
@RequiredArgsConstructor
public class StatisticController {

    private final Statistic aopTime;

    @GetMapping
    public String getStatisticsProduct() {
       return "Время на выполнение методов Product: " + aopTime.getTimerProductService() + " " +
               "Время на выполнение методов User: " + aopTime.getTimerUserService() + " " +
               "Время на выполнение методов Order: " + aopTime.getTimerOrderService();
    }
}
