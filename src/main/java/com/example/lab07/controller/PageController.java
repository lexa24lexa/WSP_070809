package com.example.lab07.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

  @GetMapping("/")
  public String landingPage() {
    return "list";
  }

  @GetMapping("/about")
  public String aboutPage() {
    return "about";
  }

  @GetMapping("/products/{id}")
  public String detailsPage() {
    return "products/details";
  }

  @GetMapping("/products/new")
  public String newProductPage() {
    return "products/new";
  }

  @GetMapping("/products/edit/{id}")
  public String editProductPage() {
    return "products/edit";
  }
}
