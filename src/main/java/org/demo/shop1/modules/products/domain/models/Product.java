package org.demo.shop1.modules.products.domain.models;

import java.util.Date;

import lombok.Data;

@Data
public class Product {

    private String id;

    private String sku;

    private String name;

    private Integer categoryId;

    private String description;

    private Double unitPrice;

    private String imageUrl;

    private Boolean isActive;

    private Integer unitsInStock;

    private Date dateCreated;

    private Date lastUpdated;
}