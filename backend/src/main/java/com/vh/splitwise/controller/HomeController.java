package com.vh.splitwise.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/home")
public class HomeController {

  @GetMapping
  public String home() {
    return "Accessible";
  }

}
