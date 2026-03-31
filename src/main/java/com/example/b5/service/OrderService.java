package com.example.b5.service;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.b5.model.CartItem;
import com.example.b5.model.Order;
import com.example.b5.model.OrderDetail;
import com.example.b5.model.Product;
import com.example.b5.repository.OrderDetailRepository;
import com.example.b5.repository.OrderRepository;
import com.example.b5.repository.ProductRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.example.b5.model.Account;
import com.example.b5.repository.AccountRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CartService cartService;

    @Transactional
    public Order checkout(String customerName, String address, String phoneNumber) {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setCustomerName(customerName);
        order.setAddress(address);
        order.setPhoneNumber(phoneNumber);
        order.setTotalAmount(cartService.getAmount());

        // Lấy thông tin user hiện tại
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String loginName = authentication.getName();
            Account account = accountRepository.findByLoginName(loginName).orElse(null);
            order.setAccount(account);
        }

        order = orderRepository.save(order);

        Collection<CartItem> items = cartService.getItems();
        for (CartItem cartItem : items) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setQuantity(cartItem.getQuantity());
            detail.setPrice(cartItem.getPrice());
            
            Product product = productRepository.findById(cartItem.getProductId()).orElse(null);
            detail.setProduct(product);
            
            orderDetailRepository.save(detail);
        }

        cartService.clear();
        return order;
    }
}
