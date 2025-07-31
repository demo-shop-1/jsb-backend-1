package org.demo.shop1.modules.products.domain.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductException extends RuntimeException {

    public ProductException(String messageCodeParam, String messageRawParam) {
        super(messageRawParam, new RuntimeException(messageCodeParam));
    }
}
