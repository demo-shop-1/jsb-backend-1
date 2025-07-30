package org.demo.shop1.modules.products.adapters.db;

import org.demo.shop1.modules.products.adapters.mappers.ProductMapper;
import org.demo.shop1.modules.products.domain.models.Product;
import org.demo.shop1.modules.products.domain.ports.out.ProductCommandOutRepository;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ProductCommandMongoRepository implements ProductCommandOutRepository {

    private final ReactiveMongoTemplate mongoTemplate;

    @Override
    public Mono<Product> save(Product product) {
        return mongoTemplate.insert(ProductMapper.toProductEntity(product))
                .flatMap(p -> Mono.just(ProductMapper.toProduct(p)));
    }

}
