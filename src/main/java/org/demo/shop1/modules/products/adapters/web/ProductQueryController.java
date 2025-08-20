package org.demo.shop1.modules.products.adapters.web;

import java.util.Optional;

import org.demo.shop1.modules.products.adapters.mappers.ProductMapper;
import org.demo.shop1.modules.products.application.dto.ProductAllAppDTO;
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
                .GET("/product", request -> {
                    Optional<String> page = request.queryParam("page");
                    Optional<String> size = request.queryParam("size");
                    Optional<String> categoryId = request.queryParam("category");

                    return productQueryService.findAll(page, size, categoryId)
                            .doFirst(() -> startMethod("/product/all"))
                            .cast(ProductAllAppDTO.class)
                            .flatMap(ProductMapper::toProductAllResponse)
                            .flatMap(productResponseDTO -> ServerResponse.ok()
                                    .bodyValue(productResponseDTO))
                            .doOnSuccess(serverResponse -> endMethod("/product/all"));
                })
                .build();
    }

}
