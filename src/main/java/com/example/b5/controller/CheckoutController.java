package com.example.b5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.b5.model.Order;
import com.example.b5.service.CartService;
import com.example.b5.service.OrderService;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;

    @GetMapping
    public String showCheckout(Model model) {
        if (cartService.getItems().isEmpty()) {
            return "redirect:/cart";
        }
        model.addAttribute("total", cartService.getAmount());
        return "checkout/index";
    }

    @PostMapping
    public String processCheckout(
            @RequestParam("customerName") String customerName,
            @RequestParam("address") String address,
            @RequestParam("phoneNumber") String phoneNumber,
            Model model) {
        
        Order order = orderService.checkout(customerName, address, phoneNumber);
        model.addAttribute("order", order);
        return "checkout/success";
    }
}
