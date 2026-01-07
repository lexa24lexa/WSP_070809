package com.example.lab07.seed;

import com.example.lab07.model.Product;
import com.example.lab07.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProductSeeder implements CommandLineRunner {

  private final ProductService productService;

  public ProductSeeder(ProductService productService) {
    this.productService = productService;
  }

  @Override
  public void run(String... args) {
    if (productService.getAllProducts().isEmpty()) {
      productService.addProduct(new Product(null, "Bread", 1.0, 5.20, "Baking"));
      productService.addProduct(new Product(null, "Butter", 0.25, 7.00, "Dairy"));
    }
  }
}
