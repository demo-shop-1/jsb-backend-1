package org.demo.shop1.product.infrastructure;

import org.demo.shop1.product.domain.model.Product;
import org.demo.shop1.product.domain.repository.ProductCommandRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

@Repository
public class ProductCommandRepositoryImpl implements ProductCommandRepository {

    @Override
    public Mono<Product> save(Product product) {
        return Mono.empty();
    }

    @Override
    public Mono<Void> delete(Product product) {
        return Mono.empty();
    }

}
