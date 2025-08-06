package org.demo.shop1.modules.products.adapters.web;

import org.demo.shop1.modules.products.adapters.dto.ProductSaveRequestDTO;
import org.demo.shop1.modules.products.adapters.mappers.ProductMapper;
import org.demo.shop1.modules.products.domain.services.ProductCommandService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ProductCommandController extends ProductController {

    private final ProductCommandService productCommandApplication;

    @PostConstruct
    public void init() {
        nameClass = "ProductCommandController";
    }

    @Bean
    public RouterFunction<ServerResponse> productRoute() {
        return RouterFunctions.route()
                .POST("/product/save",
                        req -> req.bodyToMono(ProductSaveRequestDTO.class)
                                .doFirst(() -> startMethod("/product/save"))
                                .flatMap(ProductMapper::toProduct)
                                .flatMap(productCommandApplication::createProduct)
                                .flatMap(ProductMapper::toProductSaveResponse)
                                .flatMap(productResponseDTO -> ServerResponse.ok().bodyValue(productResponseDTO))
                                .doOnSuccess(serverResponse -> endMethod("/product/save")))
                .build();
    }
}
