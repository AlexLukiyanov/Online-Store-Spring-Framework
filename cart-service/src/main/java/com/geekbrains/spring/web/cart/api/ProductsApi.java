package com.geekbrains.spring.web.cart.api;

import com.geekbrains.spring.web.cart.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "Products", url = "http://localhost:8189/web-market-core/api/v1/products")
public interface ProductsApi {

    @RequestMapping(method = RequestMethod.GET, value = "")
    List<ProductDto> getAllProducts();

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    ProductDto getProductById(@PathVariable Long id);

    @RequestMapping(method = RequestMethod.POST)
    ProductDto saveNewProduct(@RequestBody ProductDto productDto);

    @RequestMapping(method = RequestMethod.PUT)
    ProductDto updateProduct(@RequestBody ProductDto productDto);

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    void deleteById(@PathVariable Long id);
}


