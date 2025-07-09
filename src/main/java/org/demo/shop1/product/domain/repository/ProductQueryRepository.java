package org.demo.shop1.product.domain.repository;

import org.demo.shop1.product.domain.model.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductQueryRepository {

    public Flux<Product> findAll();

    public Mono<Product> findById(Integer id);

    public Flux<Product> findByCategoryId(Integer id);

    public Flux<Product> findByNameContaining(String name);

}
