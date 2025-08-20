package org.demo.shop1.modules.categories.adapters.db;

import org.demo.shop1.modules.categories.adapters.mappers.CategoryMapper;
import org.demo.shop1.modules.categories.domain.models.Category;
import org.demo.shop1.modules.categories.domain.ports.out.CategoryCommandOutRepository;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class CategoryCommandMongoRepository implements CategoryCommandOutRepository {
    
    private final ReactiveMongoTemplate mongoTemplate;

    @Override
    public Mono<Category> save(Category category) {
        return mongoTemplate.insert(CategoryMapper.toCategoryEntity(category), "categories")
                .flatMap(categoryResult -> Mono.just(CategoryMapper.toCategory(categoryResult)));
    }

}
