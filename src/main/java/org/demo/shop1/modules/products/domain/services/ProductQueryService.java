package org.demo.shop1.modules.products.domain.services;

import org.demo.shop1.modules.products.domain.models.Product;

import reactor.core.publisher.Mono;

public interface ProductQueryService {

    public Mono<Product> findBySku(String sku);

}
