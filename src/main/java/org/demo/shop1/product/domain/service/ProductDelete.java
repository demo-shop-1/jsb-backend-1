package org.demo.shop1.product.domain.service;

import org.demo.shop1.product.domain.model.Product;

import reactor.core.publisher.Mono;

public interface ProductDelete {

    public Mono<Void> delete(Product product);
    
}
