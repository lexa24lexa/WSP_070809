package com.example.lab07.controller;

import com.example.lab07.model.Product;
import com.example.lab07.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  // r all products
  @GetMapping
  public String listProducts(Model model) {
    model.addAttribute("products", productService.getAllProducts());
    return "list";
  }

  // r details
  @GetMapping("/{id}")
  public String showDetails(@PathVariable Long id, Model model) {
    productService.getProductById(id).ifPresent(p -> model.addAttribute("product", p));
    return "details";
  }

  // u
  @GetMapping("/edit/{id}")
  public String editProductForm(@PathVariable Long id, Model model) {
    productService.getProductById(id).ifPresent(p -> model.addAttribute("product", p));
    return "edit";
  }

  @PostMapping("/update/{id}")
  public String updateProduct(@PathVariable Long id, @ModelAttribute Product product) {
    productService.updateProduct(id, product);
    return "redirect:/products";
  }


  // d id
  @GetMapping("/delete/{id}")
  public String deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
    return "redirect:/products";
  }

  // c
  @GetMapping("/new")
  public String newProductForm(Model model) {
    model.addAttribute("product", new Product());
    return "new";
  }

  @PostMapping("/save")
  public String saveProduct(@ModelAttribute Product product) {
    productService.addProduct(product);
    return "redirect:/products";
  }

}
