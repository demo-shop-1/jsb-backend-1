package org.demo.shop1.modules.products.domain.services;

import org.demo.shop1.modules.products.domain.Product;
import org.demo.shop1.modules.products.domain.models.ProductModel;

import reactor.core.publisher.Mono;

public interface ProductValidationService {

    Mono<Product> validateBeforeSave(ProductModel product);

    Mono<Product> validateIfCategoryExist(ProductModel product);

    Mono<Product> validateBeforeUpdate(ProductModel product);
}
