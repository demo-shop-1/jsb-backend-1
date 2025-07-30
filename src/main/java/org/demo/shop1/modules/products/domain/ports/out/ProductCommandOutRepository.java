package org.demo.shop1.modules.products.domain.ports.out;

import org.demo.shop1.modules.products.domain.models.Product;

import reactor.core.publisher.Mono;

public interface ProductCommandOutRepository {
    Mono<Product> save(Product Product);
}
