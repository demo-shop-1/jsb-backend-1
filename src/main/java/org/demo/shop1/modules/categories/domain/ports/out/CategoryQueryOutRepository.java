package org.demo.shop1.modules.categories.domain.ports.out;

import org.demo.shop1.modules.categories.domain.models.CategoryModel;

import reactor.core.publisher.Mono;

public interface CategoryQueryOutRepository {

    public Mono<CategoryModel> findById(Integer id);
    public Mono<CategoryModel> findByName(String name);
}
