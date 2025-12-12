package com.example.lab07.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class Product {

  private Long id;

  @NotBlank(message = "Name is required")
  private String name;

  @NotBlank(message = "Category is required")
  private String category;

  @NotNull(message = "Price is required")
  @Positive(message = "Price must be positive")
  private Double price;

  @NotBlank(message = "Description is required")
  private String description;

  public Product() {}

  public Product(Long id, String name, String category, Double price, String description) {
    this.id = id;
    this.name = name;
    this.category = category;
    this.price = price;
    this.description = description;
  }

  // getters e setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public String getCategory() { return category; }
  public void setCategory(String category) { this.category = category; }

  public Double getPrice() { return price; }
  public void setPrice(Double price) { this.price = price; }

  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }

  @Override
  public String toString() {
    return "Product{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", category='" + category + '\'' +
            ", price=" + price +
            ", description='" + description + '\'' +
            '}';
  }
}
