package com.example.cloud.service.product;

import com.example.cloud.common.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@SpringBootApplication
@EnableEurekaClient
@RestController
public class ProductController {
    private final ProductService productService;

    private static final Function<Product, ProductDto> mapper = p -> new ProductDto(p.getId(), p.getTitle(), p.getPrice());

    @GetMapping
    public List<ProductDto> findAll() {
        return productService.findAll().stream().map(mapper).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return mapper.apply(productService.findById(id).get());
    }
}
