package org.demo.shop1.modules.products.domain.exceptions;

public class ProductSaveException extends ProductException {

    public ProductSaveException(String messageCodeParam, String messageRawParam) {
        super(messageCodeParam, messageRawParam);
    }

}
