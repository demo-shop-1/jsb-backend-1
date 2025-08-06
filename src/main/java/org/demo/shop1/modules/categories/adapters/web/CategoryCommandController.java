package org.demo.shop1.modules.categories.adapters.web;

import org.demo.shop1.modules.categories.adapters.dto.CategorySaveRequestDTO;
import org.demo.shop1.modules.categories.adapters.mappers.CategoryMapper;
import org.demo.shop1.modules.categories.domain.services.CategoryCommandService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class CategoryCommandController extends CategoryController {

    private final CategoryCommandService categoryCommandApplication;

    @PostConstruct
    public void init() {
        nameClass = "CategoryCommandController";
    }

    @Bean
    public RouterFunction<ServerResponse> categoryRoute() {
        return RouterFunctions.route()
                .POST("/category/save",
                        request -> request.bodyToMono(CategorySaveRequestDTO.class)
                                .doFirst(() -> startMethod("/category/save"))
                                .flatMap(CategoryMapper::toCategory)
                                .flatMap(categoryCommandApplication::createCategory)
                                .flatMap(CategoryMapper::toCategorySaveResponse)
                                .flatMap(categoryResponse -> ServerResponse.ok().bodyValue(categoryResponse))
                                .doOnSuccess(response -> endMethod("/category/save")))
                .build();
    }
}
