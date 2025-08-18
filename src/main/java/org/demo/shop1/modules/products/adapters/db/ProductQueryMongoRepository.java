package org.demo.shop1.modules.products.adapters.db;

import org.demo.shop1.modules.products.adapters.entities.ProductEntity;
import org.demo.shop1.modules.products.adapters.mappers.ProductMapper;
import org.demo.shop1.modules.products.domain.models.ProductModel;
import org.demo.shop1.modules.products.domain.ports.out.ProductQueryOutRepository;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ProductQueryMongoRepository implements ProductQueryOutRepository {

    private final ReactiveMongoTemplate mongoTemplate;

    @Override
    public Mono<ProductModel> findBySku(String sku) {
        return mongoTemplate.findOne(new Query().addCriteria(Criteria.where("sku").is(sku)),
                ProductEntity.class, "products")
                .flatMap((existingProduct) -> {
                    return Mono.just(ProductMapper.toProductModel(existingProduct));
                });
    }

}
