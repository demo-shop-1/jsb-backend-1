package org.demo.shop1.modules.categories.domain.ports.out;


import org.demo.shop1.modules.categories.domain.models.Category;

import reactor.core.publisher.Mono;

public interface CategoryCommandOutRepository {

    Mono<Category> save(Category category);

}
