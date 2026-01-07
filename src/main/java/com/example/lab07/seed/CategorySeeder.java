package com.example.lab07.seed;

import com.example.lab07.model.Category;
import com.example.lab07.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CategorySeeder implements CommandLineRunner {

  private final CategoryService categoryService;

  public CategorySeeder(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @Override
  public void run(String... args) {
    if (categoryService.getAllCategories().isEmpty()) {
      categoryService.addCategory(new Category(null, "Baking", "Baking"));
      categoryService.addCategory(new Category(null, "Dairy", "Dairy"));
    }
  }
}
