package org.demo.shop1.modules.categories.adapters.db;

import org.demo.shop1.modules.categories.adapters.entities.CategoryEntity;
import org.demo.shop1.modules.categories.adapters.mappers.CategoryMapper;
import org.demo.shop1.modules.categories.domain.models.Category;
import org.demo.shop1.modules.categories.domain.ports.out.CategoryQueryOutRepository;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class CategoryQueryMongoRepository implements CategoryQueryOutRepository {

    private final ReactiveMongoTemplate mongoTemplate;

    @Override
    public Mono<Category> findById(Integer id) {
        return mongoTemplate.findOne(new Query().addCriteria(Criteria.where("id").is(id)),
                CategoryEntity.class, "categories")
                .flatMap((category) -> {
                    return Mono.just(CategoryMapper.toCategory(category));
                });
    }

}
