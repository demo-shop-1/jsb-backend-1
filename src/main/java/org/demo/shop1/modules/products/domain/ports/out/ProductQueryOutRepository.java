package org.demo.shop1.modules.products.domain.ports.out;

import org.demo.shop1.modules.products.domain.models.ProductModel;

import reactor.core.publisher.Mono;

public interface ProductQueryOutRepository {

    public Mono<ProductModel> findBySku(String sku);

}
