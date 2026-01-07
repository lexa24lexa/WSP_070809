package com.example.lab07.model;

import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class Category {

  private Long id;

  @NotBlank(message = "Name is required")
  private String name;

  @NotBlank(message = "Code is required")
  private String code;

  private List<Long> productIds = new ArrayList<>();

  public Category() {}

  public Category(Long id, String name, String code) {
    this.id = id;
    this.name = name;
    this.code = code;
  }

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public String getCode() { return code; }
  public void setCode(String code) { this.code = code; }

  public List<Long> getProductIds() { return productIds; }
  public void setProductIds(List<Long> productIds) { this.productIds = productIds; }
}
