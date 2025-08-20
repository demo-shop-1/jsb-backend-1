package org.demo.shop1.modules.products.domain.services;

import java.util.Optional;

import org.demo.shop1.modules.products.domain.Product;

import reactor.core.publisher.Mono;

public interface ProductQueryService {

    public Mono<Product> findBySku(String sku);

    public Mono<Product> findOne(String sku);

    public Mono<Product> findAll(Optional<String> page, Optional<String> size, Optional<String> categoryFilter);

}
