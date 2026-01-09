package com.example.lab07.controller;

import com.example.lab07.model.Product;
import com.example.lab07.service.ProductService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/cart")
public class CartController {

  private final ProductService productService;

  public CartController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public String viewCart(HttpServletRequest request, Model model) {
    Map<Product, Integer> cartItems = new HashMap<>();

    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().startsWith("prod")) {
          try {
            Long prodId = Long.parseLong(cookie.getName().substring(4));
            int qty = Integer.parseInt(cookie.getValue());
            productService.getProductById(prodId).ifPresent(p -> cartItems.put(p, qty));
          } catch (NumberFormatException ignored) {}
        }
      }
    }

    double total = cartItems.entrySet().stream()
            .mapToDouble(e -> e.getKey().getPrice() * e.getValue())
            .sum();

    model.addAttribute("cartItems", cartItems);
    model.addAttribute("total", total);

    return "products/cart";
  }

  @PostMapping("/add/{id}")
  public String addToCart(@PathVariable Long id, @RequestParam(defaultValue = "1") int quantity,
                          HttpServletRequest request, HttpServletResponse response) {
    int newQuantity = quantity;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("prod" + id)) {
          newQuantity += Integer.parseInt(cookie.getValue());
          break;
        }
      }
    }
    Cookie cookie = new Cookie("prod" + id, String.valueOf(newQuantity));
    cookie.setPath("/");
    cookie.setMaxAge(7 * 24 * 60 * 60);
    response.addCookie(cookie);
    return "redirect:/cart";
  }

  @PostMapping("/update/{id}")
  public String updateCart(@PathVariable Long id,
                           @RequestParam int quantity,
                           HttpServletResponse response) {
    if (quantity <= 0) {
      Cookie cookie = new Cookie("prod" + id, null);
      cookie.setPath("/");
      cookie.setMaxAge(0);
      response.addCookie(cookie);
    } else {
      Cookie cookie = new Cookie("prod" + id, String.valueOf(quantity));
      cookie.setPath("/");
      cookie.setMaxAge(7 * 24 * 60 * 60);
      response.addCookie(cookie);
    }
    return "redirect:/cart";
  }

  @PostMapping("/remove/{id}")
  public String removeFromCart(@PathVariable Long id, HttpServletResponse response) {
    Cookie cookie = new Cookie("prod" + id, null);
    cookie.setPath("/");
    cookie.setMaxAge(0);
    response.addCookie(cookie);
    return "redirect:/cart";
  }
}
