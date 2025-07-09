package org.demo.shop1.product.infrastructure;

import org.demo.shop1.product.domain.model.Product;
import org.demo.shop1.product.domain.repository.ProductQueryRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ProductQueryRepositoryImpl implements ProductQueryRepository {

    @Override
    public Flux<Product> findAll() {
        return Flux.empty();
    }

    @Override
    public Mono<Product> findById(Integer id) {
        return Mono.empty();
    }

    @Override
    public Flux<Product> findByCategoryId(Integer id) {
        return Flux.empty();
    }

    @Override
    public Flux<Product> findByNameContaining(String name) {
        return Flux.empty();
    }

}
