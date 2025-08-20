package org.demo.shop1.modules.products.domain.exceptions;

public class ProductCommandException extends ProductException {

    public ProductCommandException(String messageCode, String messageRaw) {
        super(messageCode, messageRaw);
    }

}
