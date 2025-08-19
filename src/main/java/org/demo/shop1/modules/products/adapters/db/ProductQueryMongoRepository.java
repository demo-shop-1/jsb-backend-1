package org.demo.shop1.modules.products.adapters.db;

import java.util.List;

import org.demo.shop1.modules.products.adapters.entities.ProductEntity;
import org.demo.shop1.modules.products.adapters.mappers.ProductMapper;
import org.demo.shop1.modules.products.domain.enums.ProductFieldEnum;
import org.demo.shop1.modules.products.domain.models.ProductModel;
import org.demo.shop1.modules.products.domain.ports.out.ProductQueryOutRepository;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Repository
@RequiredArgsConstructor
public class ProductQueryMongoRepository implements ProductQueryOutRepository {

    private final ReactiveMongoTemplate mongoTemplate;

    @Override
    public Mono<ProductModel> findBySku(String sku) {
        return mongoTemplate.findOne(new Query().addCriteria(Criteria.where(ProductFieldEnum.SKU.value).is(sku)),
                ProductEntity.class, ProductFieldEnum.COLLECTION_NAME.value)
                .flatMap((existingProduct) -> {
                    return Mono.just(ProductMapper.toProductModel(existingProduct));
                });
    }

    @Override
    public Mono<Tuple2<List<ProductModel>, Long>> findAll(Query query) {
        return mongoTemplate.find(query, ProductEntity.class, ProductFieldEnum.COLLECTION_NAME.value)
                .flatMap((existingProduct) -> {
                    return Mono.just(ProductMapper.toProductModel(existingProduct));
                })
                .collectList()
                .zipWith(mongoTemplate.count(Query.of(query).limit(-1).skip(-1), ProductEntity.class));
    }

}
