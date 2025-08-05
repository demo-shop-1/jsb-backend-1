package org.demo.shop1.modules.categories.domain.services;

import org.demo.shop1.modules.categories.domain.models.Category;

import reactor.core.publisher.Mono;

public interface CategoryCommanService {

    Mono<Category> createCategory(Category category);
}
