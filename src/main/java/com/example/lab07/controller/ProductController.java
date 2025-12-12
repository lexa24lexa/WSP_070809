package com.example.lab07.controller;

import com.example.lab07.model.Product;
import com.example.lab07.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  // r all
  @GetMapping
  public String listProducts(Model model) {
    model.addAttribute("products", productService.getAllProducts());
    model.addAttribute("pageTitle", "Product List");
    return "list";
  }

  // r id
  @GetMapping("/{id}")
  public String showDetails(@PathVariable Long id, Model model) {
    productService.getProductById(id).ifPresent(p -> model.addAttribute("product", p));
    model.addAttribute("pageTitle", "Product Details");
    return "details";
  }

  // c
  @GetMapping("/new")
  public String newProductForm(Model model) {
    model.addAttribute("product", new Product());
    model.addAttribute("pageTitle", "Add New Product");
    return "new";
  }

  // c save
  @PostMapping("/save")
  public String saveProduct(@Valid @ModelAttribute("product") Product product,
                            BindingResult result,
                            Model model) {
    if (result.hasErrors()) {
      model.addAttribute("pageTitle", "Add New Product");
      return "new";
    }

    productService.addProduct(product);
    return "redirect:/products";
  }

  // u
  @GetMapping("/edit/{id}")
  public String editProductForm(@PathVariable Long id, Model model) {
    productService.getProductById(id).ifPresent(p -> model.addAttribute("product", p));
    model.addAttribute("pageTitle", "Edit Product");
    return "edit";
  }

  // u save
  @PostMapping("/update/{id}")
  public String updateProduct(@PathVariable Long id,
                              @Valid @ModelAttribute("product") Product product,
                              BindingResult result,
                              Model model) {
    if (result.hasErrors()) {
      model.addAttribute("pageTitle", "Edit Product");
      return "edit";
    }

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
