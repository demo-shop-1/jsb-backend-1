package org.demo.shop1.modules.products.domain.services;

import org.demo.shop1.modules.products.domain.exceptions.ProductSaveException;
import org.demo.shop1.modules.products.domain.models.Product;

import reactor.core.publisher.Mono;

public interface ProductCommandService {

    Mono<Product> createProduct(Product product) throws ProductSaveException;

}
