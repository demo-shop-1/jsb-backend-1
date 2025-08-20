package org.demo.shop1.modules.categories.domain.utils;

import org.demo.shop1.modules.categories.domain.enums.CategoryMessageEnum;
import org.demo.shop1.modules.categories.domain.exceptions.CategoryValidationException;
import org.demo.shop1.modules.categories.domain.models.Category;

import reactor.core.publisher.Mono;

public class CategoryUtil {
    public static Mono<Category> error(CategoryMessageEnum message) {
        return Mono.error(new CategoryValidationException(message.code, message.message));
    }
}
