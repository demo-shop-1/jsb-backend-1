package org.demo.shop1.product.domain.service;

import org.demo.shop1.product.domain.model.Product;

import reactor.core.publisher.Mono;

public interface ProductSave {
    public Mono<Product> save(Product product);
}
