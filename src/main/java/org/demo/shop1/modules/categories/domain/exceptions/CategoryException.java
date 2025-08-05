package org.demo.shop1.modules.categories.domain.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CategoryException extends RuntimeException {
    
    public CategoryException(String messageCode, String messageRaw) {
        super(messageRaw, new RuntimeException(messageCode));
    }
}
