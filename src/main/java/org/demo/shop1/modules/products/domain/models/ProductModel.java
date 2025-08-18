package org.demo.shop1.modules.products.domain.models;

import java.util.Date;

import org.demo.shop1.modules.products.domain.Product;

import lombok.Data;

@Data
public class ProductModel implements Product {

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