package com.example.project2.controller;

import com.example.project2.repository.CustomerRepository;
import com.example.project2.repository.ProductRepository;
import com.example.project2.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final OrderService orderService;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @GetMapping("/")
    public String showIndexPage(Model model) {
        model.addAttribute("orders", orderService.getAllOrderDetails());

        model.addAttribute("customers", customerRepository.findAll());
        model.addAttribute("products", productRepository.findAll());
        return "index";
    }

    @GetMapping("/chat")
    public String showChatPage() {
        return "chat";
    }

}
