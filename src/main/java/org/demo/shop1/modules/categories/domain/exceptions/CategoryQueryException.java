package org.demo.shop1.modules.categories.domain.exceptions;

public class CategoryQueryException extends CategoryException {

    public CategoryQueryException(String messageCode, String messageRaw) {
        super(messageCode, messageRaw);
    }

}
