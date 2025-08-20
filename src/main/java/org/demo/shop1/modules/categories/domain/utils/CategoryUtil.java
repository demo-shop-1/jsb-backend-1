package org.demo.shop1.modules.categories.domain.utils;

import org.demo.shop1.modules.categories.domain.Category;
import org.demo.shop1.modules.categories.domain.enums.CategoryMessageEnum;
import org.demo.shop1.modules.categories.domain.exceptions.CategoryQueryException;
import org.demo.shop1.modules.categories.domain.exceptions.CategoryValidationException;

import reactor.core.publisher.Mono;

public class CategoryUtil {
    public static Mono<Category> throwValidationError(CategoryMessageEnum message) {
        return Mono.error(new CategoryValidationException(message.code, message.message));
    }

    public static Mono<Category> throwQueryError(CategoryMessageEnum message) {
        return Mono.error(new CategoryQueryException(message.code, message.message));
    }
}
