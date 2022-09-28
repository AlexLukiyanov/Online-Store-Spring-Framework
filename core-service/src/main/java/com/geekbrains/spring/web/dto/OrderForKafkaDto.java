package com.geekbrains.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderForKafkaDto {

        private String username;
        private OrderDetailsDto orderDetailsDto;
        private String cartName;
    }
