package org.demo.shop1.modules.products.adapters.web;

import org.demo.shop1.modules.products.adapters.mappers.ProductMapper;
import org.demo.shop1.modules.products.domain.models.ProductModel;
import org.demo.shop1.modules.products.domain.services.ProductQueryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ProductQueryController extends ProductController {

    private final ProductQueryService productQueryService;

    @PostConstruct
    public void init() {
        nameClass = "ProductQueryController";
    }

    @Bean
    public RouterFunction<ServerResponse> productQueryRoutes() {
        return RouterFunctions.route()
                .GET("/product/{id}", request -> productQueryService.findOne(request.pathVariable("id"))
                        .doFirst(() -> startMethod("/product/getOne"))
                        .cast(ProductModel.class)
                        .flatMap(ProductMapper::toProductSingleResponse)
                        .flatMap(productResponseDTO -> ServerResponse.ok()
                                .bodyValue(productResponseDTO))
                        .doOnSuccess(serverResponse -> endMethod("/product/getOne")))
                .build();
    }

}
