package org.demo.shop1.modules.products.domain.exceptions;

public class ProductSkuException extends ProductException {

    public ProductSkuException(int messageCode, String messageRaw) {
        super(messageCode, messageRaw);
    }
}
