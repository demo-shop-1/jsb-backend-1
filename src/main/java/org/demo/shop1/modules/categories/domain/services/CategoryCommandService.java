package org.demo.shop1.modules.categories.domain.services;

import org.demo.shop1.modules.categories.domain.models.CategoryModel;

import reactor.core.publisher.Mono;

public interface CategoryCommandService {

    Mono<CategoryModel> createCategory(CategoryModel category);
}
