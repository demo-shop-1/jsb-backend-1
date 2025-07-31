package org.demo.shop1.modules.products.adapters.entities;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotEmpty(message = "SKU is required")
    @Size(min = 4, message = "SKU minimum 4 characters")
    private String sku;

    @NotEmpty(message = "Name is required")
    private String name;

    @NotNull(message = "Category ID is required")
    private Integer categoryId;

    @NotEmpty(message = "Description is required")
    private String description;

    @NotNull(message = "Unit Price is required")
    @Min(value = 0, message = "Unit Price minimum value is 0")
    private Double unitPrice;

    private String imageUrl;

    private Boolean isActive;

    @NotNull(message = "Units In Stock is required")
    @Min(value = 0, message = "Units In Stock minimum value is 0")
    private Integer unitsInStock;

    @NotNull(message = "Date Created is required")
    private Date dateCreated;

    private Date lastUpdated;
}
