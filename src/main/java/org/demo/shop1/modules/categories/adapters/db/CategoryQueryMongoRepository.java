package org.demo.shop1.modules.categories.adapters.db;

import java.util.regex.Pattern;

import org.demo.shop1.modules.categories.adapters.entities.CategoryEntity;
import org.demo.shop1.modules.categories.adapters.mappers.CategoryMapper;
import org.demo.shop1.modules.categories.domain.models.CategoryModel;
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
    public Mono<CategoryModel> findById(Integer id) {
        return mongoTemplate.findOne(
                new Query().addCriteria(Criteria.where("id").is(id)),
                CategoryEntity.class, "categories")
                .flatMap((category) -> {
                    return Mono.just(CategoryMapper.toCategory(category));
                });
    }

    @Override
    public Mono<CategoryModel> findByName(String name) {
        return mongoTemplate.findOne(
                new Query().addCriteria(Criteria.where("name").regex(String.format("^%s$", Pattern.quote(name)), "i")),
                CategoryEntity.class, "categories")
                .flatMap(category -> {
                    return Mono.just(CategoryMapper.toCategory(category));
                });
    }

}
