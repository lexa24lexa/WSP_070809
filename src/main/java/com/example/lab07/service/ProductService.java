package com.example.lab07.service;

import com.example.lab07.model.Product;
import com.example.lab07.model.Category;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

  private Map<Long, Product> productMap = new HashMap<>();
  private Long nextId = 1L;

  private final CategoryService categoryService;

  public ProductService(@Lazy CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  public Product addProduct(Product product) {
    Optional<Category> catOpt = categoryService.getAllCategories().stream()
            .filter(c -> c.getCode().equals(product.getCategoryCode()))
            .findFirst();

    if (catOpt.isEmpty()) {
      throw new IllegalArgumentException("Category does not exist.");
    }

    product.setId(nextId++);
    productMap.put(product.getId(), product);

    // add product to category
    catOpt.get().getProductIds().add(product.getId());

    return product;
  }

  public List<Product> getAllProducts() {
    return new ArrayList<>(productMap.values());
  }

  public Optional<Product> getProductById(Long id) {
    return Optional.ofNullable(productMap.get(id));
  }

  public Optional<Product> updateProduct(Long id, Product updatedProduct) {
    if (!productMap.containsKey(id)) return Optional.empty();

    Product oldProduct = productMap.get(id);

    if (!oldProduct.getCategoryCode().equals(updatedProduct.getCategoryCode())) {
      categoryService.getAllCategories().stream()
              .filter(c -> c.getCode().equals(oldProduct.getCategoryCode()))
              .findFirst()
              .ifPresent(c -> c.getProductIds().remove(id));

      Optional<Category> newCat = categoryService.getAllCategories().stream()
              .filter(c -> c.getCode().equals(updatedProduct.getCategoryCode()))
              .findFirst();

      if (newCat.isEmpty()) throw new IllegalArgumentException("New category does not exist.");

      newCat.get().getProductIds().add(id);
    }

    updatedProduct.setId(id);
    productMap.put(id, updatedProduct);
    return Optional.of(updatedProduct);
  }

  public boolean deleteProduct(Long id) {
    Product p = productMap.remove(id);
    if (p != null) {
      categoryService.getAllCategories().stream()
              .filter(c -> c.getCode().equals(p.getCategoryCode()))
              .findFirst()
              .ifPresent(c -> c.getProductIds().remove(id));
      return true;
    }
    return false;
  }

  public List<Product> getProductsByCategory(String categoryCode) {
    return productMap.values().stream()
            .filter(p -> p.getCategoryCode().equals(categoryCode))
            .collect(Collectors.toList());
  }
}
