package org.demo.shop1.modules.categories.domain.exceptions;

public class CategoryValidationException extends CategoryException {

    public CategoryValidationException(String messageCode, String messageRaw) {
        super(messageCode, messageRaw);
    }

}
