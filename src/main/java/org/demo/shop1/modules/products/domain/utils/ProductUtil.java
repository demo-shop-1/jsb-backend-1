package org.demo.shop1.modules.products.domain.utils;

import org.demo.shop1.modules.products.domain.enums.ProductMessageEnum;
import org.demo.shop1.modules.products.domain.exceptions.ProductValidationException;
import org.demo.shop1.modules.products.domain.models.Product;

import reactor.core.publisher.Mono;

public class ProductUtil {
    public static Mono<Product> error(ProductMessageEnum message) {
        return Mono.error(new ProductValidationException(message.code, message.message));
    }
}
