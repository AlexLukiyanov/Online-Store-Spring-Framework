package com.geekbrains.spring.web.controllers;

import com.geekbrains.spring.web.converters.OrderConverter;
import com.geekbrains.spring.web.dto.OrderDetailsDto;
import com.geekbrains.spring.web.dto.OrderDto;
import com.geekbrains.spring.web.dto.OrderForKafkaDto;
import com.geekbrains.spring.web.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
        private final OrderService orderService;
        private final OrderConverter orderConverter;

    @Qualifier(value = "KafkaForOrder")
    @Autowired
    private KafkaTemplate<Long, OrderForKafkaDto> kafkaTemplate;

    @PostMapping("/{cartName}")
    public void createOrderForKafka(@RequestHeader String username, @RequestBody OrderDetailsDto orderDetailsDto, @PathVariable String cartName) {
        ListenableFuture<SendResult<Long, OrderForKafkaDto>> future = kafkaTemplate.send("MessageForOrder", new OrderForKafkaDto(username, orderDetailsDto, cartName));
        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();
    }

    //@PostMapping("/{cartName}")
    //public void createOrder(@RequestHeader String username, @RequestBody OrderDetailsDto orderDetailsDto, @PathVariable String cartName){
    //   orderService.createOrder(username, orderDetailsDto, cartName);
    //}

    @GetMapping
    public List<OrderDto> getCurrenUrders(@RequestHeader String username){
        return orderService.findOrdersByUsername(username).stream()
                .map(orderConverter::entityToDto).collect(Collectors.toList());
    }
}
