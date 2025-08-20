package org.demo.shop1.modules.products.adapters.web;

import org.demo.shop1.modules.products.adapters.dto.ProductSaveRequestDTO;
import org.demo.shop1.modules.products.adapters.dto.ProductSaveResponseDTO;
import org.demo.shop1.modules.products.adapters.dto.ProductUpdateRequestDTO;
import org.demo.shop1.modules.products.adapters.mappers.ProductMapper;
import org.demo.shop1.modules.products.domain.models.ProductModel;
import org.demo.shop1.modules.products.domain.services.ProductCommandService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Configuration
@RequiredArgsConstructor
public class ProductCommandController extends ProductController {

        private final ProductCommandService productCommandApplication;

        @PostConstruct
        public void init() {
                nameClass = "ProductCommandController";
        }

        @Bean
        public RouterFunction<ServerResponse> productCommandRoutes() {
                return RouterFunctions.route()
                                .POST("/product/save", request -> request.bodyToMono(ProductSaveRequestDTO.class)
                                                .doFirst(() -> startMethod("/product/save"))
                                                .flatMap(ProductMapper::toProductModel)
                                                .flatMap(productCommandApplication::createProduct)
                                                .cast(ProductModel.class)
                                                .flatMap(ProductMapper::toProductSaveResponse)
                                                .flatMap(productResponseDTO -> ServerResponse.ok()
                                                                .bodyValue(productResponseDTO))
                                                .doOnSuccess(serverResponse -> endMethod(
                                                                "/product/save")))
                                .POST("/product/save/all", request -> {
                                        Flux<ProductSaveResponseDTO> responses = request
                                                        .bodyToFlux(ProductSaveRequestDTO.class)
                                                        .doFirst(() -> startMethod("/product/save/all"))
                                                        .flatMap(ProductMapper::toProductModel)
                                                        .flatMap(productCommandApplication::createProduct)
                                                        .cast(ProductModel.class)
                                                        .flatMap(ProductMapper::toProductSaveResponse);

                                        return ServerResponse.ok()
                                                        .contentType(MediaType.APPLICATION_JSON) // streaming
                                                                                                 // JSON
                                                        .body(responses, ProductSaveResponseDTO.class)
                                                        .doOnSuccess(serverResponse -> endMethod(
                                                                        "/product/save/all"));
                                })
                                .PUT("/product/update", request -> request.bodyToMono(ProductUpdateRequestDTO.class)
                                                .doFirst(() -> startMethod("/product/update"))
                                                .flatMap(ProductMapper::toProductModel)
                                                .flatMap(productCommandApplication::updateProduct)
                                                .cast(ProductModel.class)
                                                .flatMap(ProductMapper::toProductUpdateResponse)
                                                .flatMap(productResponseDTO -> ServerResponse.ok()
                                                                .bodyValue(productResponseDTO))
                                                .doOnSuccess(serverResponse -> endMethod(
                                                                "/product/update")))
                                .DELETE("/product/delete/{id}",
                                                request -> productCommandApplication
                                                                .deleteProduct(request.pathVariable("id"))
                                                                .doFirst(() -> startMethod("/product/delete"))
                                                                .cast(ProductModel.class)
                                                                .flatMap(ProductMapper::toProductDeleteResponse)
                                                                .flatMap(productResponseDTO -> ServerResponse.ok()
                                                                                .bodyValue(productResponseDTO))
                                                                .doOnSuccess(serverResponse -> endMethod(
                                                                                "/product/delete")))
                                .build();
        }
}
