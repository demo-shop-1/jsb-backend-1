package org.demo.shop1.modules.products.domain.enums;

import java.util.HashMap;
import java.util.Map;

public enum ProductMessageEnum {

    // field CODE has to be unique
    PRODUCT_ERROR("PROD-1", "There is an error with this product"),
    SKU_BLANK("PROD-2", "SKU is blank"),
    SKU_REPEAT("PROD-3", "SKU already exists"),
    NAME_BLANK("PROD-4", "Name is blank"),
    CATEGORY_NULL("PROD-4", "Category is emtpy"),
    DESCRIPTION_BLANK("PROD-5", "Description is blank"),
    SKU_MIN("PROD-6", "SKU is shorter"),
    UNIT_PRICE_MIN("PROD-7", "Unit Price has to be a positive value"),
    UNIT_PRICE_NULL("PROD-8", "Unit Price is empty"),
    UNIT_IN_STOCK_NULL("PROD-9", "Unit In Stock is empty"),
    UNIT_IN_STOCK_MIN("PROD-10", "Unit In Stock has to be a positive value");

    private static final Map<String, ProductMessageEnum> BY_CODE = new HashMap<>();
    private static final Map<String, ProductMessageEnum> BY_MESSAGE = new HashMap<>();

    static {
        for (ProductMessageEnum e : values()) {
            BY_CODE.put(e.code, e);
            BY_MESSAGE.put(e.message, e);
        }
    }

    public final String code;
    public final String message;

    private ProductMessageEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ProductMessageEnum valueOfCode(String code) {
        return BY_CODE.get(code);
    }

    public static ProductMessageEnum valueOfMessage(String message) {
        return BY_MESSAGE.get(message);
    }
}
