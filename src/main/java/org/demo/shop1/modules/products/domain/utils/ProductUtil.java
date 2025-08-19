package org.demo.shop1.modules.products.domain.utils;

import org.demo.shop1.modules.products.domain.Product;
import org.demo.shop1.modules.products.domain.enums.ProductMessageEnum;
import org.demo.shop1.modules.products.domain.exceptions.ProductQueryException;
import org.demo.shop1.modules.products.domain.exceptions.ProductValidationException;

import reactor.core.publisher.Mono;

public class ProductUtil {
    public static Mono<Product> throwValidationError(ProductMessageEnum message) {
        return Mono.error(new ProductValidationException(message.code, message.message));
    }

    public static Mono<Product> throwQueryError(ProductMessageEnum message) {
        return Mono.error(new ProductQueryException(message.code, message.message));
    }
}
