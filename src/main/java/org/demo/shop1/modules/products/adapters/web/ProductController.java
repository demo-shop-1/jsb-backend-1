package org.demo.shop1.modules.products.adapters.web;

import org.demo.shop1.modules.products.adapters.dto.ProductSaveRequestDTO;
import org.demo.shop1.modules.products.adapters.mappers.ProductMapper;
import org.demo.shop1.modules.products.domain.services.ProductCommandService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ProductController {

    private final ProductCommandService productCommandService;

    @Bean
    public RouterFunction<ServerResponse> productRoute() {
        return RouterFunctions.route()
                .POST("/product/save",
                        req -> req.bodyToMono(ProductSaveRequestDTO.class)
                                .flatMap(ProductMapper::toProductSaveRequest)
                                .flatMap(productCommandService::createProduct)
                                .flatMap(ProductMapper::toProductSaveResponse)
                                .flatMap(p -> ServerResponse.ok().bodyValue(p)))
                .build();
    }
}
