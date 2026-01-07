package com.example.lab07.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class Product {

  private Long id;

  @NotBlank(message = "Name is required")
  private String name;

  @NotNull(message = "Weight is required")
  @Positive(message = "Weight must be positive")
  private Double weight;

  @NotNull(message = "Price is required")
  @Positive(message = "Price must be positive")
  private Double price;

  @NotBlank(message = "Category is required")
  private String categoryCode;

  public Product() {}

  public Product(Long id, String name, Double weight, Double price, String categoryCode) {
    this.id = id;
    this.name = name;
    this.weight = weight;
    this.price = price;
    this.categoryCode = categoryCode;
  }

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public Double getWeight() { return weight; }
  public void setWeight(Double weight) { this.weight = weight; }

  public Double getPrice() { return price; }
  public void setPrice(Double price) { this.price = price; }

  public String getCategoryCode() { return categoryCode; }
  public void setCategoryCode(String categoryCode) { this.categoryCode = categoryCode; }
}
