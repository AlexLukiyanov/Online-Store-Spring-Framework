package com.geekbrains.spring.web.endpoints;

import com.geekbrains.spring.web.services.ProductsService;
import com.geekbrains.spring.web.soap.soap.GetAllProductsRequest;
import com.geekbrains.spring.web.soap.soap.GetAllProductsResponse;
import com.geekbrains.spring.web.soap.soap.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {

    private static final String NAMESPACE_URI = "http://www.mvg.com/spring/ws/products";
    private final ProductsService productsService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllUsers(@RequestPayload GetAllProductsRequest request){
        GetAllProductsResponse response = new GetAllProductsResponse();
        productsService.findAllProducts().forEach(p -> {
            Product product = new Product();
            product.setId(p.getId());
            product.setTitle(p.getTitle());
            product.setPrice(p.getPrice());
            response.getProducts().add(product);
        });
        return response;
    }
}
