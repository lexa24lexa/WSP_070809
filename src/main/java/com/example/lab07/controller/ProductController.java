package com.example.lab07.controller;

import com.example.lab07.model.Product;
import com.example.lab07.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  // r all
  @GetMapping
  public List<Product> getAllProducts() {
    return productService.getAllProducts();
  }

  // r by id
  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable Long id) {
    return productService.getProductById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }

  // c
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Product createProduct(@Valid @RequestBody Product product) {
    return productService.addProduct(product);
  }

  // u
  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(
          @PathVariable Long id,
          @Valid @RequestBody Product product) {

    return productService.updateProduct(id, product)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }

  // d
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    if (productService.deleteProduct(id)) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }
}
