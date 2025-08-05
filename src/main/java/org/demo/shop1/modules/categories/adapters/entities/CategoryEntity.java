package org.demo.shop1.modules.categories.adapters.entities;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "categories")
public class CategoryEntity {

    @MongoId
    Integer id;

    @NotEmpty(message = "Name is required")
    @Size(min = 4, message = "Name minimum 4 characters")
    String name;
}
