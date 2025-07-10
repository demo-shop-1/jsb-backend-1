package org.demo.shop1.product.infrastructure.inbound.controllers;

import org.demo.shop1.product.domain.model.Product;
import org.demo.shop1.product.domain.service.ProductSave;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ProductController {
    private final ProductSave productSaveService;

    @Bean
    public RouterFunction<ServerResponse> productRoute() {
        return RouterFunctions.route()
                .POST("/product/save",
                        req -> req.bodyToMono(Product.class)
                                .flatMap(productSaveService::save)
                                .flatMap(p -> ServerResponse.ok().bodyValue(p)))
                .build();
    }

}
