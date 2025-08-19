package org.demo.shop1.modules.products.domain.exceptions;

public class ProductPaginationException extends ProductException {

    public ProductPaginationException(String messageCode, String messageRaw) {
        super(messageCode, messageRaw);
    }

}
