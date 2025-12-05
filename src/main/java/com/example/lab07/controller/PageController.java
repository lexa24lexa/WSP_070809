package com.example.lab07.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("pageTitle", "Home");
    return "index";
  }

  @GetMapping("/about")
  public String about(Model model) {
    model.addAttribute("pageTitle", "About Author");
    return "about";
  }
}
