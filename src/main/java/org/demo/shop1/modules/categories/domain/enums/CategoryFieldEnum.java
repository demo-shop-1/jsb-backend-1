package org.demo.shop1.modules.categories.domain.enums;

public enum CategoryFieldEnum {

    COLLECTION_NAME("categories"),
    ID("id"),
    NAME("name"),
    DESCRIPTION("description"),
    IS_ACTIVE("isActive"),
    DATE_CREATED("dateCreated"),
    LAST_UPDATED("lastUpdated");

    public final String value;

    private CategoryFieldEnum(String value) {
        this.value = value;
    }

}
