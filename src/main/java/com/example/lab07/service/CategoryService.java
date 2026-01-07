package com.example.lab07.service;

import com.example.lab07.model.Category;
import com.example.lab07.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {

  private Map<Long, Category> categoryMap = new HashMap<>();
  private Long nextId = 1L;

  private final ProductService productService;

  public CategoryService(ProductService productService) {
    this.productService = productService;
  }

  public Category addCategory(Category category) {
    category.setId(nextId++);
    categoryMap.put(category.getId(), category);
    return category;
  }

  public List<Category> getAllCategories() {
    return new ArrayList<>(categoryMap.values());
  }

  public Optional<Category> getCategoryById(Long id) {
    return Optional.ofNullable(categoryMap.get(id));
  }

  public Optional<Category> updateCategory(Long id, Category updated) {
    if (categoryMap.containsKey(id)) {
      updated.setId(id);
      updated.setProductIds(categoryMap.get(id).getProductIds());
      categoryMap.put(id, updated);
      return Optional.of(updated);
    }
    return Optional.empty();
  }

  public boolean deleteCategory(Long id) {
    Category cat = categoryMap.remove(id);
    if (cat != null) {
      // Remove all products in this category
      for (Long pid : cat.getProductIds()) {
        productService.deleteProduct(pid);
      }
      return true;
    }
    return false;
  }
}
