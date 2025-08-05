package org.demo.shop1.modules.products.domain.exceptions;

public class ProductValidationException extends ProductException {

    public ProductValidationException(String messageCode, String messageRaw) {
        super(messageCode, messageRaw);
    }

}
