package org.demo.shop1.modules.products.adapters.entities;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class ProductEntity {
    @Id
    private String id;

    @NotEmpty
    private String sku;

    @NotEmpty
    private String name;

    @NotNull
    private Integer categoryId;

    @NotEmpty
    private String description;

    @NotNull
    private Double unitPrice;

    private String imageUrl;

    private Boolean isActive;

    @NotNull
    private Integer unitsInStock;

    private Date dateCreated;

    private Date lastUpdated;
}
