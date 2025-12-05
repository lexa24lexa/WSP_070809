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

  // r add
  @GetMapping
  public String listProducts(Model model) {
    model.addAttribute("products", productService.getAllProducts());
    model.addAttribute("pageTitle", "Product List");
    return "list"; // render list.html
  }

  // r id
  @GetMapping("/{id}")
  public String showDetails(@PathVariable Long id, Model model) {
    productService.getProductById(id).ifPresent(p -> model.addAttribute("product", p));
    model.addAttribute("pageTitle", "Product Details");
    return "details"; // render details.html
  }

  // c
  @GetMapping("/new")
  public String newProductForm(Model model) {
    model.addAttribute("product", new Product());
    model.addAttribute("pageTitle", "Add New Product");
    return "new"; // render new.html
  }

  // c save
  @PostMapping("/save")
  public String saveProduct(@ModelAttribute Product product) {
    productService.addProduct(product);
    return "redirect:/products";
  }

  // u
  @GetMapping("/edit/{id}")
  public String editProductForm(@PathVariable Long id, Model model) {
    productService.getProductById(id).ifPresent(p -> model.addAttribute("product", p));
    model.addAttribute("pageTitle", "Edit Product");
    return "edit"; // render edit.html
  }

  // u save
  @PostMapping("/update/{id}")
  public String updateProduct(@PathVariable Long id, @ModelAttribute Product product) {
    productService.updateProduct(id, product);
    return "redirect:/products";
  }

  // d
  @GetMapping("/delete/{id}")
  public String deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
    return "redirect:/products";
  }
}
