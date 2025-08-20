package org.demo.shop1.modules.categories.domain.models;

import lombok.Data;

@Data
public class CategoryModel {
    private Integer id;
    private String name;
    private String description;
    private Boolean isActive;
}
