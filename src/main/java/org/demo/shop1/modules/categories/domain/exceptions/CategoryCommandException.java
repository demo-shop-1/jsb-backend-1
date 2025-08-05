package org.demo.shop1.modules.categories.domain.exceptions;

public class CategoryCommandException extends CategoryException {

    public CategoryCommandException(String messageCode, String messageRaw) {
        super(messageCode, messageRaw);
    }

}
