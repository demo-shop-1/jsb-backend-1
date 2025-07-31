package org.demo.shop1.modules.products.domain.enums;

import java.util.HashMap;
import java.util.Map;

public enum ProductEnum {

    // field CODE has to be unique
    PRODUCT_ERROR("PROD-1", "There is an error with this product"),
    SKU_BLANK("PROD-2", "SKU is blank"),
    SKU_REPEAT("PROD-3", "SKU already exists");

    private static final Map<String, ProductEnum> BY_CODE = new HashMap<>();
    private static final Map<String, ProductEnum> BY_MESSAGE = new HashMap<>();

    static {
        for (ProductEnum e : values()) {
            BY_CODE.put(e.code, e);
            BY_MESSAGE.put(e.message, e);
        }
    }

    public final String code;
    public final String message;

    private ProductEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ProductEnum valueOfCode(String code) {
        return BY_CODE.get(code);
    }

    public static ProductEnum valueOfMessage(String message) {
        return BY_MESSAGE.get(message);
    }
}
