package org.demo.shop1.product.domain.repository;

import org.demo.shop1.product.domain.model.Product;

import reactor.core.publisher.Mono;

public interface ProductCommandRepository {

    public Mono<Product> save(Product product);

    public Mono<Void> delete(Product product);

}
