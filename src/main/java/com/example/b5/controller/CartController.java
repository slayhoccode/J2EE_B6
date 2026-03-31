package com.example.b5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.b5.model.Product;
import com.example.b5.service.CartService;
import com.example.b5.service.ProductService;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;

    @GetMapping
    public String viewCart(Model model) {
        model.addAttribute("items", cartService.getItems());
        model.addAttribute("total", cartService.getAmount());
        return "cart/view";
    }

    @GetMapping("/add/{id}")
    public String addCart(@PathVariable("id") Integer id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            cartService.add(product);
        }
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateCart(@RequestParam("id") Integer id, @RequestParam("quantity") Integer quantity) {
        cartService.update(id, quantity);
        return "redirect:/cart";
    }

    @GetMapping("/remove/{id}")
    public String removeCart(@PathVariable("id") Integer id) {
        cartService.remove(id);
        return "redirect:/cart";
    }

    @GetMapping("/clear")
    public String clearCart() {
        cartService.clear();
        return "redirect:/cart";
    }
}
