package org.demo.shop1.modules.categories.domain.ports.out;


import org.demo.shop1.modules.categories.domain.models.CategoryModel;

import reactor.core.publisher.Mono;

public interface CategoryCommandOutRepository {

    Mono<CategoryModel> save(CategoryModel category);

}
