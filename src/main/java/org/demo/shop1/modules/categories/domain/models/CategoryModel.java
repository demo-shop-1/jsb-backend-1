package org.demo.shop1.modules.categories.domain.models;

import java.util.Date;

import org.demo.shop1.modules.categories.domain.Category;

import lombok.Data;

@Data
public class CategoryModel implements Category {
    private Integer id;
    private String name;
    private String description;
    private Boolean isActive;
    private Date dateCreated;
    private Date lastUpdated;
}
