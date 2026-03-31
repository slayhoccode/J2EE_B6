package com.example.b5.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.example.b5.model.CartItem;
import com.example.b5.model.Product;

@Service
@SessionScope
public class CartService {
    private Map<Integer, CartItem> cart = new HashMap<>();

    public void add(Product product) {
        CartItem item = cart.get(product.getId());
        if (item == null) {
            item = new CartItem();
            item.setProductId(product.getId());
            item.setName(product.getName());
            item.setPrice(product.getPrice());
            item.setImage(product.getImage());
            item.setQuantity(1);
            cart.put(product.getId(), item);
        } else {
            item.setQuantity(item.getQuantity() + 1);
        }
    }

    public void remove(int id) {
        cart.remove(id);
    }

    public void update(int id, int quantity) {
        CartItem item = cart.get(id);
        if (item != null) {
            item.setQuantity(quantity);
        }
    }

    public void clear() {
        cart.clear();
    }

    public Collection<CartItem> getItems() {
        return cart.values();
    }

    public int getCount() {
        return cart.values().stream().mapToInt(item -> item.getQuantity()).sum();
    }

    public long getAmount() {
        return cart.values().stream().mapToLong(item -> item.getPrice() * item.getQuantity()).sum();
    }
}
