package org.demo.shop1.product.infrastructure;

import org.demo.shop1.product.domain.model.Product;
import org.demo.shop1.product.domain.repository.ProductCommandRepository;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ProductCommandRepositoryImpl implements ProductCommandRepository {

    private final ReactiveMongoTemplate mongoDb;

    @Override
    public Mono<Product> save(Product product) {
        return  mongoDb.insert(product);
    }

    @Override
    public Mono<Void> delete(Product product) {
        // TODO: have to call someone
        return Mono.empty();
    }

}
