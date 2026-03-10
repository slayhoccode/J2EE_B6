package com.example.b5.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.b5.model.Product;
import com.example.b5.service.CategoryService;
import com.example.b5.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    // Hiển thị danh sách
    @GetMapping
    public String listProducts(Model model) {
        List<Product> productList = productService.getAllProducts();
        model.addAttribute("products", productList);
        return "product/list";
    }

    // Hiển thị form thêm
    @GetMapping("/add")
    public String showAddForm(Model model) {

        Product product = new Product();
        model.addAttribute("product", product);

        model.addAttribute("categories", categoryService.getAllCategories());

        return "product/add";
    }

    // Lưu product
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product) {

        productService.saveProduct(product);

        return "redirect:/products";
    }

    // Hiển thị form edit
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {

        Product product = productService.getProductById(id);

        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories());

        return "product/edit";
    }

    // Xóa product
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id) {

        productService.deleteProduct(id);

        return "redirect:/products";
    }
}