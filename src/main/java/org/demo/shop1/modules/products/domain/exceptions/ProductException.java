package org.demo.shop1.modules.products.domain.exceptions;

import org.demo.shop1.modules.products.domain.Product;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductException extends RuntimeException implements Product {

    public ProductException(String messageCode, String messageRaw) {
        super(messageRaw, new RuntimeException(messageCode));
    }
}
