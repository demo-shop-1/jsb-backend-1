package org.demo.shop1.modules.products.domain.services;

import org.demo.shop1.modules.products.domain.Product;
import org.demo.shop1.modules.products.domain.models.ProductModel;

import reactor.core.publisher.Mono;

public interface ProductCommandService {

    Mono<Product> createProduct(ProductModel product);

    Mono<Product> updateProduct(ProductModel product);

}
