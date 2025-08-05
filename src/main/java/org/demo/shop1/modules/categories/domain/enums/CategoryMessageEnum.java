package org.demo.shop1.modules.categories.domain.enums;

import java.util.HashMap;
import java.util.Map;

public enum CategoryMessageEnum {

    // field code has to be unique
    ID_INVALID("CATE-1", "ID has to be a positive value"),
    ID_REPEATED("CATE-2", "This ID already exists"),
    NAME_REPEATED("CATE-3", "This Name already exists");

    private static final Map<String, CategoryMessageEnum> BY_CODE = new HashMap<>();
    private static final Map<String, CategoryMessageEnum> BY_MESSAGE = new HashMap<>();
    public final String code;
    public final String message;

    static {
        for (CategoryMessageEnum value : values()) {
            BY_CODE.put(value.code, value);
            BY_MESSAGE.put(value.message, value);
        }
    }

    private CategoryMessageEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static CategoryMessageEnum valueOfCode(String code) {
        return BY_CODE.get(code);
    }

    public static CategoryMessageEnum valueOfMessage(String message) {
        return BY_MESSAGE.get(message);
    }
}
